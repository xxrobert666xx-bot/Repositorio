package com.example.ventasbolivar.service.serviceimpl;

import com.example.ventasbolivar.model.dto.ArticuloVentaResponse;
import com.example.ventasbolivar.model.dto.DetalleVentaResponse;
import com.example.ventasbolivar.model.dto.SelectOptionDto;
import com.example.ventasbolivar.model.dto.SerieNumeroResponse;
import com.example.ventasbolivar.model.dto.VentaRequest;
import com.example.ventasbolivar.model.dto.VentaResponse;
import com.example.ventasbolivar.model.entity.Articulo;
import com.example.ventasbolivar.model.entity.DetalleVenta;
import com.example.ventasbolivar.model.entity.Persona;
import com.example.ventasbolivar.model.entity.Usuario;
import com.example.ventasbolivar.model.entity.Venta;
import com.example.ventasbolivar.repository.ArticuloRepository;
import com.example.ventasbolivar.repository.DetalleVentaRepository;
import com.example.ventasbolivar.repository.PersonaRepository;
import com.example.ventasbolivar.repository.UsuarioRepository;
import com.example.ventasbolivar.repository.VentaRepository;
import com.example.ventasbolivar.service.VentaService;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio de ventas.
 */
@Service
@RequiredArgsConstructor
public class VentaServiceimpl implements VentaService {

  private static final Logger log = LoggerFactory.getLogger(VentaServiceimpl.class);

  private final VentaRepository ventaRepo;
  private final DetalleVentaRepository detalleRepo;
  private final PersonaRepository personaRepo;
  private final UsuarioRepository usuarioRepo;
  private final ArticuloRepository articuloRepo;

  @Override
  public List<VentaResponse> listar() {
    log.info("Listando todas las ventas");

    List<VentaResponse> lista = ventaRepo.findAll()
        .stream().map(this::toResponse)
        .collect(Collectors.toList());

    log.info("Total ventas encontradas: {}", lista.size());
    return lista;
  }

  @Override
  public VentaResponse obtener(Integer id) {
    log.info("Obteniendo venta con ID {}", id);

    return ventaRepo.findById(id)
     .map(v -> {
       log.debug("Venta encontrada: Serie {} - Número {}",
           v.getSerieComprobante(), v.getNumComprobante());
       return toResponse(v);
     })
     .orElseThrow(() -> {
       log.warn("Venta con ID {} no encontrada", id);
       return new NoSuchElementException("Venta no encontrada");
     });
  }

  @Override
  @Transactional
  public VentaResponse crear(VentaRequest req) {
    log.info("Creando nueva venta para cliente ID {} por usuario ID {}",
        req.getIdCliente(), req.getIdUsuario());

    Persona cliente = personaRepo.findById(req.getIdCliente())
        .orElseThrow(() -> {
          log.warn("Cliente ID {} no encontrado", req.getIdCliente());
          return new NoSuchElementException("Cliente no encontrado");
        });

    Usuario usuario = usuarioRepo.findById(req.getIdUsuario())
        .orElseThrow(() -> {
          log.warn("Usuario ID {} no encontrado", req.getIdUsuario());
          return new NoSuchElementException("Usuario no encontrado");
        });

    Venta v = new Venta();
    v.setCliente(cliente);
    v.setUsuario(usuario);
    v.setTipoComprobante(req.getTipoComprobante());
    v.setSerieComprobante(req.getSerieComprobante());
    v.setNumComprobante(req.getNumComprobante());
    v.setFechaHora(req.getFechaHora() == null ? LocalDateTime.now() : req.getFechaHora());
    v.setImpuesto(req.getImpuesto());
    v.setTotalVenta(req.getTotalVenta());
    v.setEstado("Aceptado");

    Venta saved = ventaRepo.save(v);
    log.info("Venta registrada con ID {}", saved.getId());

    List<DetalleVenta> detalles = req.getDetalles().stream().map(dr -> {
      log.debug("Procesando detalle para artículo ID {}", dr.getIdArticulo());

      Articulo art = articuloRepo.findById(dr.getIdArticulo())
          .orElseThrow(() -> {
            log.warn("Artículo ID {} no encontrado", dr.getIdArticulo());
            return new NoSuchElementException("Artículo no encontrado");
          });

      if (art.getStock() < dr.getCantidad()) {
        log.error("Stock insuficiente. Artículo {} - Stock actual: {}, solicitado: {}",
            art.getId(), art.getStock(), dr.getCantidad());
        throw new IllegalArgumentException("Stock insuficiente para el artículo id " + art.getId());
      }

      art.setStock(art.getStock() - dr.getCantidad());
      articuloRepo.save(art);
      log.debug("Stock actualizado. Artículo {} nuevo stock: {}", art.getId(), art.getStock());

      DetalleVenta dv = new DetalleVenta();
      dv.setVenta(saved);
      dv.setArticulo(art);
      dv.setCantidad(dr.getCantidad());
      dv.setPrecioVenta(dr.getPrecioVenta());
      dv.setDescuento(dr.getDescuento() == null ? BigDecimal.ZERO : dr.getDescuento());
      return dv;
    }).collect(Collectors.toList());

    detalleRepo.saveAll(detalles);

    saved.setDetalles(detalles);
    ventaRepo.save(saved);

    log.info("Venta {} creada con {} detalles", saved.getId(), detalles.size());

    return toResponse(saved);
  }

  @Override
  @Transactional
  public VentaResponse anular(Integer id) {
    log.info("Anulando venta con ID {}", id);

    Venta v = ventaRepo.findById(id)
        .orElseThrow(() -> {
          log.warn("Venta con ID {} no encontrada para anular", id);
          return new NoSuchElementException("Venta no encontrada");
        });

    v.setEstado("Anulado");
    ventaRepo.save(v);

    log.info("Venta {} anulada correctamente", id);
    return toResponse(v);
  }

  @Override
  public List<SelectOptionDto> selectClientes() {
    log.info("Listando clientes para Select");

    return personaRepo.findAll().stream()
     .filter(p -> "Cliente".equalsIgnoreCase(p.getTipoPersona()))
     .map(p -> new SelectOptionDto(p.getId(), p.getNombre()))
     .collect(Collectors.toList());
  }

  @Override
  public List<ArticuloVentaResponse> listarArticulosActivosVenta() {
    log.info("Listando artículos activos para venta");

    return articuloRepo.findAll().stream()
     .filter(a -> a.getCondicion() != null && a.getCondicion() == 1)
     .map(a -> new ArticuloVentaResponse(
      a.getId(),
      a.getNombre(),
      a.getCategoria() != null ? a.getCategoria().getNombre() : "",
      a.getCodigo(),
      a.getStock(),
      a.getPrecioVenta(),
      0.0,
      a.getImagen()
     ))
     .collect(Collectors.toList());
  }

  @Override
  public SerieNumeroResponse nextSerieNumero(String tipo) {
    log.info("Generando siguiente serie y número para comprobante tipo {}", tipo);

    String prefijo = switch (tipo == null ? "Boleta" : tipo) {
      case "Factura" -> "F";
      case "Ticket" -> "T";
      default -> "B";
    };

    String serieFinal = prefijo + "V001";
    Integer lastNum = ventaRepo.findMaxNumComprobanteBySerie(serieFinal);

    log.debug("Último número encontrado para serie {}: {}", serieFinal, lastNum);

    String numero = lastNum == null ? "000001" : String.format("%06d", lastNum + 1);

    return new SerieNumeroResponse(serieFinal, numero);
  }

  @Override
  public List<DetalleVentaResponse> listarDetalle(Integer idVenta) {
    log.info("Listando detalles de venta ID {}", idVenta);

    return detalleRepo.findByVentaId(idVenta)
     .stream()
     .map(d -> new DetalleVentaResponse(
      d.getId(),
      d.getArticulo().getId(),
      d.getArticulo().getNombre(),
      d.getCantidad(),
      d.getPrecioVenta(),
      d.getDescuento()
     ))
     .collect(Collectors.toList());
  }

  /* Helpers */
  private VentaResponse toResponse(Venta v) {
    VentaResponse resp = new VentaResponse();
    resp.setId(v.getId());
    resp.setIdCliente(v.getCliente() != null ? v.getCliente().getId() : null);
    resp.setNombreCliente(v.getCliente() != null ? v.getCliente().getNombre() : null);
    resp.setIdUsuario(v.getUsuario() != null ? v.getUsuario().getId() : null);
    resp.setTipoComprobante(v.getTipoComprobante());
    resp.setSerieComprobante(v.getSerieComprobante());
    resp.setNumComprobante(v.getNumComprobante());
    resp.setFechaHora(v.getFechaHora());
    resp.setImpuesto(v.getImpuesto());
    resp.setTotalVenta(v.getTotalVenta());
    resp.setEstado(v.getEstado());

    if (v.getDetalles() != null) {
      resp.setDetalles(v.getDetalles().stream()
          .map(d -> new DetalleVentaResponse(
          d.getId(),
          d.getArticulo().getId(),
          d.getArticulo().getNombre(),
          d.getCantidad(),
          d.getPrecioVenta(),
          d.getDescuento()
       ))
          .collect(Collectors.toList()));
    }
    return resp;
  }
}