package com.example.ventasbolivar.service.serviceimpl;

import com.example.ventasbolivar.model.dto.DetalleIngresoRequest;
import com.example.ventasbolivar.model.dto.DetalleIngresoResponse;
import com.example.ventasbolivar.model.dto.IngresoRequest;
import com.example.ventasbolivar.model.dto.IngresoResponse;
import com.example.ventasbolivar.model.entity.Articulo;
import com.example.ventasbolivar.model.entity.DetalleIngreso;
import com.example.ventasbolivar.model.entity.Ingreso;
import com.example.ventasbolivar.repository.ArticuloRepository;
import com.example.ventasbolivar.repository.DetalleIngresoRepository;
import com.example.ventasbolivar.repository.IngresoRepository;
import com.example.ventasbolivar.service.IngresoService;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * IngresoServiceImpl.
 */
@Service
public class IngresoServiceimpl implements IngresoService {

  private static final Logger logger = LoggerFactory.getLogger(IngresoServiceimpl.class);

  private final IngresoRepository ingresoRepository;
  private final DetalleIngresoRepository detalleRepository;
  private final ArticuloRepository articuloRepository;

  /**
   * IngresoServiceImpl.
   */
  public IngresoServiceimpl(
      IngresoRepository ingresoRepository,
      DetalleIngresoRepository detalleRepository,
      ArticuloRepository articuloRepository) {

    this.ingresoRepository = ingresoRepository;
    this.detalleRepository = detalleRepository;
    this.articuloRepository = articuloRepository;
  }

  @Override
  public IngresoResponse registrarIngreso(IngresoRequest request) {

    logger.info("Registrando ingreso proveedor={} usuario={} total={}",
        request.getIdproveedor(), request.getIdusuario(), request.getTotalcompra());

    Ingreso ingreso = new Ingreso();
    ingreso.setIdproveedor(request.getIdproveedor());
    ingreso.setIdusuario(request.getIdusuario());
    ingreso.setTipoComprobante(request.getTipoComprobante());
    ingreso.setSerieComprobante(request.getSerieComprobante());
    ingreso.setNumComprobante(request.getNumComprobante());
    ingreso.setFechahora(LocalDateTime.now());
    ingreso.setImpuesto(request.getImpuesto());
    ingreso.setTotalcompra(request.getTotalcompra());
    ingreso.setEstado("Aceptado");

    ingreso.setDetalles(new ArrayList<>());

    ingreso = ingresoRepository.save(ingreso);
    logger.debug("Ingreso guardado con ID={}", ingreso.getIdingreso());

    for (DetalleIngresoRequest detReq : request.getDetalles()) {

      logger.debug("Procesando detalle articulo={} cantidad={}",
          detReq.getIdarticulo(), detReq.getCantidad());

      DetalleIngreso det = new DetalleIngreso();
      det.setIngreso(ingreso);
      det.setIdarticulo(detReq.getIdarticulo());
      det.setCantidad(detReq.getCantidad());
      det.setPrecioCompra(detReq.getPrecioCompra());
      det.setPrecioVenta(detReq.getPrecioVenta());

      Articulo articulo = articuloRepository.findById(detReq.getIdarticulo())
          .orElseThrow(() -> {
            logger.error("Artículo no encontrado {}", detReq.getIdarticulo());
            return new RuntimeException("Artículo no encontrado");
          });

      detalleRepository.save(det);
      ingreso.getDetalles().add(det);

      articulo.setStock(articulo.getStock() + detReq.getCantidad());
      articuloRepository.save(articulo);

      logger.debug("Stock actualizado articulo={} stock={}",
          articulo.getId(), articulo.getStock());
    }

    logger.info("Ingreso {} registrado correctamente", ingreso.getIdingreso());
    return entityToResponse(ingreso);
  }

  @Override
  public List<IngresoResponse> listar() {
    logger.info("Listando ingresos");
    return ingresoRepository.findAll()
     .stream()
     .map(this::entityToResponse)
     .collect(Collectors.toList());
  }

  @Override
  public IngresoResponse obtenerPorId(Integer id) {
    logger.info("Buscando ingreso ID={}", id);

    Ingreso ingreso = ingresoRepository.findById(id)
        .orElseThrow(() -> {
          logger.error("Ingreso no encontrado ID={}", id);
          return new RuntimeException("Ingreso no encontrado");
        });

    return entityToResponse(ingreso);
  }

  @Override
  public IngresoResponse anularIngreso(Integer id) {

    logger.warn("Intentando anular ingreso ID={}", id);

    Ingreso ingreso = ingresoRepository.findById(id)
        .orElseThrow(() -> {
          logger.error("Ingreso no encontrado ID={}", id);
          return new RuntimeException("Ingreso no encontrado");
        });

    if (ingreso.getEstado().equalsIgnoreCase("Anulado")) {
      logger.warn("Ingreso ID={} ya estaba anulado", id);
      throw new RuntimeException("El ingreso ya se encuentra anulado");
    }

    ingreso.getDetalles().forEach(det -> {

      Articulo art = articuloRepository.findById(det.getIdarticulo())
          .orElseThrow(() -> {
            logger.error("Artículo no encontrado {}", det.getIdarticulo());
            return new RuntimeException("Artículo no encontrado");
          });

      art.setStock(art.getStock() - det.getCantidad());
      articuloRepository.save(art);

      logger.debug("Revirtiendo stock articulo={} nuevoStock={}",
          art.getId(), art.getStock());
    });

    ingreso.setEstado("Anulado");
    ingresoRepository.save(ingreso);

    logger.info("Ingreso ID={} anulado correctamente", id);

    return entityToResponse(ingreso);
  }

  private IngresoResponse entityToResponse(Ingreso ingreso) {
    IngresoResponse res = new IngresoResponse();

    res.setIdingreso(ingreso.getIdingreso());
    res.setIdproveedor(ingreso.getIdproveedor());
    res.setIdusuario(ingreso.getIdusuario());
    res.setTipoComprobante(ingreso.getTipoComprobante());
    res.setSerieComprobante(ingreso.getSerieComprobante());
    res.setNumComprobante(ingreso.getNumComprobante());
    res.setFechahora(ingreso.getFechahora());
    res.setImpuesto(ingreso.getImpuesto());
    res.setTotalcompra(ingreso.getTotalcompra());
    res.setEstado(ingreso.getEstado());
    res.setDetalles(
        ingreso.getDetalles().stream().map(det -> {
          DetalleIngresoResponse d = new DetalleIngresoResponse();
          d.setIddetalleIngreso(det.getIddetalleIngreso());
          d.setIdarticulo(det.getIdarticulo());
          d.setCantidad(det.getCantidad());
          d.setPrecioCompra(det.getPrecioCompra());
          d.setPrecioVenta(det.getPrecioVenta());
          return d;
        }).collect(Collectors.toList())
    );

    return res;
  }
}
