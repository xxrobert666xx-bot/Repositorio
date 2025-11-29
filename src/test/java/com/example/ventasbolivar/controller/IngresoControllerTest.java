package com.example.ventasbolivar.controller;

import com.example.ventasbolivar.model.dto.IngresoRequest;
import com.example.ventasbolivar.model.dto.IngresoResponse;
import com.example.ventasbolivar.service.IngresoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class IngresoControllerTest {

  @Mock
  private IngresoService ingresoService;

  @InjectMocks
  private IngresoController ingresoController;

  @Test
  void listar_ok() {
    when(ingresoService.listar()).thenReturn(List.of(new IngresoResponse()));

    var response = ingresoController.listar();

    assertEquals(HttpStatus.OK, response.getStatusCode());
    verify(ingresoService).listar();
  }

  @Test
  void obtener_ok() {
    IngresoResponse mockResponse = new IngresoResponse();
    mockResponse.setIdingreso(1);

    when(ingresoService.obtenerPorId(1)).thenReturn(mockResponse);

    var response = ingresoController.obtener(1);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(1, response.getBody().getIdingreso());
  }

  @Test
  void registrar_ok() {
    IngresoRequest req = new IngresoRequest();
    IngresoResponse resp = new IngresoResponse();
    resp.setIdingreso(1);

    when(ingresoService.registrarIngreso(req)).thenReturn(resp);

    var response = ingresoController.registrar(req);

    assertEquals(HttpStatus.CREATED, response.getStatusCode());
    assertEquals(1, response.getBody().getIdingreso());
  }

  @Test
  void anular_ok() {
    IngresoResponse resp = new IngresoResponse();
    resp.setIdingreso(1);

    when(ingresoService.anularIngreso(1)).thenReturn(resp);

    var response = ingresoController.anular(1);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(1, response.getBody().getIdingreso());
  }
}