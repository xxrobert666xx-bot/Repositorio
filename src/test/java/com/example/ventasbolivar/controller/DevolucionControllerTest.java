package com.example.ventasbolivar.controller;

import com.example.ventasbolivar.model.dto.DevolucionRequest;
import com.example.ventasbolivar.model.dto.DevolucionResponse;
import com.example.ventasbolivar.service.DevolucionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DevolucionControllerTest {

  @InjectMocks
  private DevolucionController devolucionController;

  @Mock
  private DevolucionService devolucionService;

  private DevolucionRequest request;
  private DevolucionResponse response;

  @BeforeEach
  void setup() {
    request = new DevolucionRequest();
    request.setIdventa(1);
    request.setMotivo("Motivo X");

    response = new DevolucionResponse();
    response.setIddevolucion(10);
    response.setMotivo("OK");
  }

  @Test
  void registrar_DevolucionExitosa() {
    when(devolucionService.registrarDevolucion(request)).thenReturn(response);

    DevolucionResponse resultado = devolucionController.registrar(request);

    assertEquals(10, resultado.getIddevolucion());
    assertEquals("OK", resultado.getMotivo());

    verify(devolucionService, times(1)).registrarDevolucion(request);
  }

  @Test
  void listar_Devoluciones() {

    DevolucionResponse r1 = new DevolucionResponse();
    r1.setIddevolucion(1);

    DevolucionResponse r2 = new DevolucionResponse();
    r2.setIddevolucion(2);

    when(devolucionService.listarDevoluciones()).thenReturn(List.of(r1, r2));

    List<DevolucionResponse> resultado = devolucionController.listar();

    assertEquals(2, resultado.size());
    assertEquals(1, resultado.get(0).getIddevolucion());
    assertEquals(2, resultado.get(1).getIddevolucion());

    verify(devolucionService, times(1)).listarDevoluciones();
  }

  @Test
  void obtener_PorId() {

    DevolucionResponse r = new DevolucionResponse();
    r.setIddevolucion(5);

    when(devolucionService.obtenerPorId(5)).thenReturn(r);

    DevolucionResponse resultado = devolucionController.obtener(5);

    assertEquals(5, resultado.getIddevolucion());
    verify(devolucionService, times(1)).obtenerPorId(5);
  }
}