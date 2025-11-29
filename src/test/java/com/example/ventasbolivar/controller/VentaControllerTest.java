package com.example.ventasbolivar.controller;

import com.example.ventasbolivar.model.dto.*;
import com.example.ventasbolivar.service.VentaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VentaControllerTest {

  @Mock
  private VentaService service;

  @InjectMocks
  private VentaController controller;

  @Test
  void listar() {
    when(service.listar()).thenReturn(List.of(new VentaResponse()));
    ResponseEntity<List<VentaResponse>> resp = controller.listar();

    assertEquals(200, resp.getStatusCodeValue());
    assertFalse(resp.getBody().isEmpty());
  }

  @Test
  void obtener() {
    VentaResponse venta = new VentaResponse();
    venta.setId(1);

    when(service.obtener(1)).thenReturn(venta);

    ResponseEntity<VentaResponse> resp = controller.obtener(1);

    assertEquals(200, resp.getStatusCodeValue());
    assertEquals(1, resp.getBody().getId());
  }

  @Test
  void crear() {
    VentaRequest req = new VentaRequest();
    VentaResponse respMock = new VentaResponse();
    respMock.setId(10);

    when(service.crear(req)).thenReturn(respMock);

    ResponseEntity<VentaResponse> resp = controller.crear(req);

    assertEquals(201, resp.getStatusCodeValue());
    assertEquals(10, resp.getBody().getId());
  }

  @Test
  void anular() {
    ResponseEntity<Void> resp = controller.anular(5);
    assertEquals(204, resp.getStatusCodeValue());
  }

  @Test
  void selectCliente() {
    when(service.selectClientes()).thenReturn(List.of(new SelectOptionDto(1, "Cliente")));

    ResponseEntity<List<SelectOptionDto>> resp = controller.selectCliente();

    assertEquals(200, resp.getStatusCodeValue());
    assertEquals(1, resp.getBody().get(0).getId());
  }

  @Test
  void listarArticulos() {
    when(service.listarArticulosActivosVenta())
     .thenReturn(List.of(new ArticuloVentaResponse()));

    ResponseEntity<List<ArticuloVentaResponse>> resp = controller.listarArticulos();

    assertEquals(200, resp.getStatusCodeValue());
  }

  @Test
  void nextSerieNumero() {
    SerieNumeroResponse mock = new SerieNumeroResponse("BV001", "000010");
    when(service.nextSerieNumero("Boleta")).thenReturn(mock);

    ResponseEntity<SerieNumeroResponse> resp = controller.nextSerieNumero("Boleta");

    assertEquals(200, resp.getStatusCodeValue());
    assertEquals("BV001", resp.getBody().getSerie());
  }

  @Test
  void listarDetalle() {
    when(service.listarDetalle(7)).thenReturn(List.of(new DetalleVentaResponse()));

    ResponseEntity<List<DetalleVentaResponse>> resp = controller.listarDetalle(7);

    assertEquals(200, resp.getStatusCodeValue());
  }
}
