package com.example.ventasbolivar.service.serviceimpl;

import com.example.ventasbolivar.model.dto.*;
import com.example.ventasbolivar.model.entity.*;
import com.example.ventasbolivar.repository.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VentaServiceimplTest {

  @Mock
  private VentaRepository ventaRepo;
  @Mock
  private DetalleVentaRepository detalleRepo;
  @Mock
  private PersonaRepository personaRepo;
  @Mock
  private UsuarioRepository usuarioRepo;
  @Mock
  private ArticuloRepository articuloRepo;

  @InjectMocks
  private VentaServiceimpl service;

  @Test
  void listar() {
    Venta venta = new Venta();
    venta.setId(1);
    venta.setEstado("Aceptado");

    when(ventaRepo.findAll()).thenReturn(List.of(venta));

    List<VentaResponse> resp = service.listar();

    assertEquals(1, resp.size());
    assertEquals(1, resp.get(0).getId());
    verify(ventaRepo).findAll();
  }

  @Test
  void obtener() {
    Venta venta = new Venta();
    venta.setId(7);
    venta.setSerieComprobante("B001");
    venta.setNumComprobante("000123");

    when(ventaRepo.findById(7)).thenReturn(Optional.of(venta));

    VentaResponse resp = service.obtener(7);

    assertEquals(7, resp.getId());
    assertEquals("B001", resp.getSerieComprobante());
    verify(ventaRepo).findById(7);
  }

  @Test
  void obtener_noEncontrado() {
    when(ventaRepo.findById(200)).thenReturn(Optional.empty());

    try {
      service.obtener(200);
    } catch (RuntimeException ex) {
      assertEquals("Venta no encontrada", ex.getMessage());
    }
  }

  @Test
  void crear() {
    Persona cliente = new Persona();
    cliente.setId(1);
    cliente.setNombre("Cliente 1");
    cliente.setTipoPersona("Cliente");

    Usuario usuario = new Usuario();
    usuario.setId(2);

    Articulo articulo = new Articulo();
    articulo.setId(3);
    articulo.setNombre("Producto 1");
    articulo.setStock(10);
    articulo.setPrecioVenta(100.00);

    Venta ventaGuardada = new Venta();
    ventaGuardada.setId(100);

    when(personaRepo.findById(1)).thenReturn(Optional.of(cliente));
    when(usuarioRepo.findById(2)).thenReturn(Optional.of(usuario));
    when(ventaRepo.save(any())).thenReturn(ventaGuardada);
    when(articuloRepo.findById(3)).thenReturn(Optional.of(articulo));

    VentaRequest req = new VentaRequest();
    req.setIdCliente(1);
    req.setIdUsuario(2);
    req.setFechaHora(LocalDateTime.now());
    req.setImpuesto(18.0);
    req.setTotalVenta(200.00);
    req.setDetalles(List.of(
     new DetalleVentaRequest(3, 2, new BigDecimal("100"), BigDecimal.ZERO)
    ));

    VentaResponse resp = service.crear(req);

    verify(articuloRepo).save(argThat(a -> a.getStock() == 8));
    verify(detalleRepo).saveAll(any());

    assertEquals(100, resp.getId());
  }

  @Test
  void crear_clienteNoEncontrado() {
    when(personaRepo.findById(1)).thenReturn(Optional.empty());

    VentaRequest req = new VentaRequest();
    req.setIdCliente(1);
    req.setIdUsuario(2);

    try {
      service.crear(req);
    } catch (RuntimeException ex) {
      assertEquals("Cliente no encontrado", ex.getMessage());
    }
  }

  @Test
  void anular() {
    Venta venta = new Venta();
    venta.setId(10);
    venta.setEstado("Aceptado");

    when(ventaRepo.findById(10)).thenReturn(Optional.of(venta));

    VentaResponse resp = service.anular(10);

    verify(ventaRepo).save(argThat(v -> "Anulado".equals(v.getEstado())));
    assertEquals("Anulado", resp.getEstado());
  }

  @Test
  void anular_noEncontrado() {
    when(ventaRepo.findById(100)).thenReturn(Optional.empty());

    try {
      service.anular(100);
    } catch (RuntimeException ex) {
      assertEquals("Venta no encontrada", ex.getMessage());
    }
  }

  @Test
  void nextSerieNumero() {
    when(ventaRepo.findMaxNumComprobanteBySerie("BV001")).thenReturn(5);

    SerieNumeroResponse resp = service.nextSerieNumero("Boleta");

    assertEquals("BV001", resp.getSerie());
    assertEquals("000006", resp.getNumero());
  }

  @Test
  void selectCliente() {
    Persona p1 = new Persona();
    p1.setId(1);
    p1.setNombre("Cliente");
    p1.setTipoPersona("Cliente");

    Persona p2 = new Persona();
    p2.setId(2);
    p2.setNombre("Proveedor");
    p2.setTipoPersona("Proveedor");

    when(personaRepo.findAll()).thenReturn(List.of(p1, p2));

    List<SelectOptionDto> resp = service.selectClientes();

    assertEquals(1, resp.size());
    assertEquals(1, resp.get(0).getId());
  }

  @Test
  void listarDetalle() {
    DetalleVenta dv = new DetalleVenta();
    dv.setId(1);

    Articulo articulo = new Articulo();
    articulo.setId(5);
    articulo.setNombre("Producto");
    dv.setArticulo(articulo);

    dv.setCantidad(3);
    dv.setPrecioVenta(new BigDecimal("50"));
    dv.setDescuento(BigDecimal.ZERO);

    when(detalleRepo.findByVentaId(20)).thenReturn(List.of(dv));

    List<DetalleVentaResponse> resp = service.listarDetalle(20);

    assertEquals(1, resp.size());
    assertEquals(5, resp.get(0).getIdArticulo());
  }

  @Test
  void listarArticulosActivosVenta() {
    Articulo a1 = new Articulo();
    a1.setId(1);
    a1.setNombre("Prod A");
    a1.setCondicion(1);
    a1.setStock(5);
    a1.setPrecioVenta(10.0);

    Articulo a2 = new Articulo();
    a2.setId(2);
    a2.setNombre("Prod B");
    a2.setCondicion(0); // NO activo

    when(articuloRepo.findAll()).thenReturn(List.of(a1, a2));

    List<ArticuloVentaResponse> resp = service.listarArticulosActivosVenta();

    assertEquals(1, resp.size());
    assertEquals(1, resp.get(0).getIdArticulo());
    assertEquals("Prod A", resp.get(0).getNombre());
  }
}
