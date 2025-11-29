package com.example.ventasbolivar.service.serviceimpl;

import com.example.ventasbolivar.model.dto.DevolucionRequest;
import com.example.ventasbolivar.model.dto.DevolucionResponse;
import com.example.ventasbolivar.model.entity.Articulo;
import com.example.ventasbolivar.model.entity.Devolucion;
import com.example.ventasbolivar.repository.ArticuloRepository;
import com.example.ventasbolivar.repository.DevolucionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DevolucionServiceimplTest {

  @Mock
  private DevolucionRepository devolucionRepository;

  @Mock
  private ArticuloRepository articuloRepository;

  @InjectMocks
  private DevolucionServiceimpl service;

  @Test
  void registrarDevolucion_exitoso() {

    DevolucionRequest request = new DevolucionRequest();
    request.setIdventa(10);
    request.setIdarticulo(5);
    request.setCantidad(3);
    request.setMotivo("Defecto");

    Articulo articulo = new Articulo();
    articulo.setId(5);
    articulo.setStock(7);

    when(articuloRepository.findById(5)).thenReturn(Optional.of(articulo));

    Devolucion devSaved = new Devolucion();
    devSaved.setIddevolucion(1);
    devSaved.setIdventa(10);
    devSaved.setIdarticulo(5);
    devSaved.setCantidad(3);
    devSaved.setMotivo("Defecto");
    devSaved.setFechaDevolucion(LocalDateTime.now());

    when(devolucionRepository.save(any(Devolucion.class))).thenReturn(devSaved);

    DevolucionResponse resp = service.registrarDevolucion(request);

    assertNotNull(resp);
    assertEquals(1, resp.getIddevolucion());
    assertEquals(10, resp.getIdventa());
    assertEquals(5, resp.getIdarticulo());
    assertEquals(3, resp.getCantidad());
    assertEquals("Defecto", resp.getMotivo());

    assertEquals(10, articulo.getStock());

    verify(articuloRepository).save(articulo);
  }

  @Test
  void registrarDevolucion_articuloNoEncontrado() {
    DevolucionRequest request = new DevolucionRequest();
    request.setIdarticulo(99);

    when(articuloRepository.findById(99)).thenReturn(Optional.empty());

    assertThrows(RuntimeException.class, () -> service.registrarDevolucion(request));
  }

  @Test
  void listarDevoluciones_exitoso() {
    Devolucion dev = new Devolucion();
    dev.setIddevolucion(1);
    dev.setIdventa(10);
    dev.setIdarticulo(5);
    dev.setCantidad(2);
    dev.setMotivo("Falla");
    dev.setFechaDevolucion(LocalDateTime.now());

    when(devolucionRepository.findAll()).thenReturn(List.of(dev));

    List<DevolucionResponse> lista = service.listarDevoluciones();

    assertEquals(1, lista.size());
    assertEquals(1, lista.get(0).getIddevolucion());
  }

  @Test
  void listarDevoluciones_listaVacia() {
    when(devolucionRepository.findAll()).thenReturn(List.of());

    List<DevolucionResponse> lista = service.listarDevoluciones();

    assertTrue(lista.isEmpty());
  }

  @Test
  void obtenerPorId_exitoso() {
    Devolucion dev = new Devolucion();
    dev.setIddevolucion(1);
    dev.setIdventa(10);

    when(devolucionRepository.findById(1)).thenReturn(Optional.of(dev));

    DevolucionResponse resp = service.obtenerPorId(1);

    assertEquals(1, resp.getIddevolucion());
    assertEquals(10, resp.getIdventa());
  }

  @Test
  void obtenerPorId_noEncontrado() {
    when(devolucionRepository.findById(50)).thenReturn(Optional.empty());

    assertThrows(RuntimeException.class, () -> service.obtenerPorId(50));
  }
}