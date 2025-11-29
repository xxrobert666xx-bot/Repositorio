package com.example.ventasbolivar.service.serviceimpl;

import com.example.ventasbolivar.model.dto.DetalleIngresoRequest;
import com.example.ventasbolivar.model.dto.IngresoRequest;
import com.example.ventasbolivar.model.entity.Articulo;
import com.example.ventasbolivar.model.entity.DetalleIngreso;
import com.example.ventasbolivar.model.entity.Ingreso;
import com.example.ventasbolivar.repository.ArticuloRepository;
import com.example.ventasbolivar.repository.DetalleIngresoRepository;
import com.example.ventasbolivar.repository.IngresoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class IngresoServiceimplTest {

  @Mock
  private IngresoRepository ingresoRepository;

  @Mock
  private DetalleIngresoRepository detalleRepository;

  @Mock
  private ArticuloRepository articuloRepository;

  @InjectMocks
  private IngresoServiceimpl ingresoService;

  private Ingreso ingreso;
  private Articulo articulo;

  @BeforeEach
  void setUp() {
    ingreso = new Ingreso();
    ingreso.setIdingreso(1);
    ingreso.setIdproveedor(10);
    ingreso.setIdusuario(5);
    ingreso.setTipoComprobante("Factura");
    ingreso.setFechahora(LocalDateTime.now());
    ingreso.setEstado("Aceptado");
    ingreso.setDetalles(new ArrayList<>());

    articulo = new Articulo();
    articulo.setId(100);
    articulo.setStock(20);
  }

  @Test
  void registrarIngreso_ok() {
    ingreso.setDetalles(new ArrayList<>());
    DetalleIngresoRequest detReq = new DetalleIngresoRequest(100, 5, new BigDecimal("10.00"), new BigDecimal("15.00"));

    IngresoRequest request = new IngresoRequest(
     10, 5, "Factura", "A1", "001",
     new BigDecimal("18.00"),
     new BigDecimal("200.00"),
     List.of(detReq)
    );

    when(ingresoRepository.save(any(Ingreso.class))).thenReturn(ingreso);
    when(articuloRepository.findById(100)).thenReturn(Optional.of(articulo));

    ingresoService.registrarIngreso(request);

    verify(ingresoRepository, times(1)).save(any());
    verify(detalleRepository, times(1)).save(any());
    verify(articuloRepository, times(1)).save(any());
  }

  @Test
  void obtenerPorId_ok() {
    ingreso.setDetalles(new ArrayList<>());
    when(ingresoRepository.findById(1)).thenReturn(Optional.of(ingreso));

    var response = ingresoService.obtenerPorId(1);

    assertNotNull(response);
    assertEquals(1, response.getIdingreso());
    verify(ingresoRepository).findById(1);
  }

  @Test
  void obtenerPorId_notFound() {
    when(ingresoRepository.findById(1)).thenReturn(Optional.empty());

    assertThrows(RuntimeException.class, () -> ingresoService.obtenerPorId(1));
  }

  @Test
  void anularIngreso_ok() {

    DetalleIngreso detalle = new DetalleIngreso();
    detalle.setIdarticulo(100);
    detalle.setCantidad(5);
    ingreso.setDetalles(List.of(detalle));

    when(ingresoRepository.findById(1)).thenReturn(Optional.of(ingreso));
    when(articuloRepository.findById(100)).thenReturn(Optional.of(articulo));

    ingresoService.anularIngreso(1);

    assertEquals("Anulado", ingreso.getEstado());
    verify(articuloRepository, times(1)).save(any());
    verify(ingresoRepository, times(1)).save(ingreso);
  }
}

