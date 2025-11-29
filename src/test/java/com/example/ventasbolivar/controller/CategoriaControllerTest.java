package com.example.ventasbolivar.controller;

import com.example.ventasbolivar.model.dto.CategoriaRequest;
import com.example.ventasbolivar.model.dto.CategoriaResponse;
import com.example.ventasbolivar.service.CategoriaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoriaControllerTest {

  @Mock
  private CategoriaService service;

  @InjectMocks
  private CategoriaController controller;

  @Test
  void listar() {
    when(service.listar()).thenReturn(List.of(new CategoriaResponse()));

    ResponseEntity<List<CategoriaResponse>> resp = controller.listar();

    assertEquals(HttpStatus.OK, resp.getStatusCode());
    assertNotNull(resp.getBody());
  }

  @Test
  void listar_errorInterno() {
    when(service.listar()).thenThrow(new RuntimeException());

    ResponseEntity<List<CategoriaResponse>> resp = controller.listar();

    assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, resp.getStatusCode());
  }

  @Test
  void obtener() {
    CategoriaResponse mockResp = new CategoriaResponse();
    when(service.obtener(1)).thenReturn(mockResp);

    ResponseEntity<CategoriaResponse> resp = controller.obtener(1);

    assertEquals(HttpStatus.OK, resp.getStatusCode());
    assertEquals(mockResp, resp.getBody());
  }

  @Test
  void obtener_noEncontrado() {
    when(service.obtener(999)).thenThrow(new NoSuchElementException());

    ResponseEntity<CategoriaResponse> resp = controller.obtener(999);

    assertEquals(HttpStatus.NOT_FOUND, resp.getStatusCode());
  }

  @Test
  void obtener_errorInterno() {
    when(service.obtener(1)).thenThrow(new RuntimeException());

    ResponseEntity<CategoriaResponse> resp = controller.obtener(1);

    assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, resp.getStatusCode());
  }

  @Test
  void crear() {
    CategoriaRequest req = new CategoriaRequest();
    CategoriaResponse saved = new CategoriaResponse();

    when(service.crear(req)).thenReturn(saved);

    ResponseEntity<CategoriaResponse> resp = controller.crear(req);

    assertEquals(HttpStatus.CREATED, resp.getStatusCode());
    assertEquals(saved, resp.getBody());
  }

  @Test
  void crear_datosInvalidos() {
    CategoriaRequest req = new CategoriaRequest();

    when(service.crear(req)).thenThrow(new IllegalArgumentException());

    ResponseEntity<CategoriaResponse> resp = controller.crear(req);

    assertEquals(HttpStatus.BAD_REQUEST, resp.getStatusCode());
  }

  @Test
  void crear_errorInterno() {
    CategoriaRequest req = new CategoriaRequest();

    when(service.crear(req)).thenThrow(new RuntimeException());

    ResponseEntity<CategoriaResponse> resp = controller.crear(req);

    assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, resp.getStatusCode());
  }

  @Test
  void actualizar() {
    CategoriaRequest req = new CategoriaRequest();
    CategoriaResponse updated = new CategoriaResponse();

    when(service.actualizar(1, req)).thenReturn(updated);

    ResponseEntity<CategoriaResponse> resp = controller.actualizar(1, req);

    assertEquals(HttpStatus.OK, resp.getStatusCode());
    assertEquals(updated, resp.getBody());
  }

  @Test
  void actualizar_noEncontrado() {
    CategoriaRequest req = new CategoriaRequest();

    when(service.actualizar(999, req)).thenThrow(new NoSuchElementException());

    ResponseEntity<CategoriaResponse> resp = controller.actualizar(999, req);

    assertEquals(HttpStatus.NOT_FOUND, resp.getStatusCode());
  }

  @Test
  void actualizar_datosInvalidos() {
    CategoriaRequest req = new CategoriaRequest();

    when(service.actualizar(1, req)).thenThrow(new IllegalArgumentException());

    ResponseEntity<CategoriaResponse> resp = controller.actualizar(1, req);

    assertEquals(HttpStatus.BAD_REQUEST, resp.getStatusCode());
  }

  @Test
  void actualizar_errorInterno() {
    CategoriaRequest req = new CategoriaRequest();

    when(service.actualizar(1, req)).thenThrow(new RuntimeException());

    ResponseEntity<CategoriaResponse> resp = controller.actualizar(1, req);

    assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, resp.getStatusCode());
  }

  @Test
  void eliminar() {
    doNothing().when(service).eliminar(1);

    ResponseEntity<Void> resp = controller.eliminar(1);

    assertEquals(HttpStatus.NO_CONTENT, resp.getStatusCode());
  }

  @Test
  void eliminar_noEncontrado() {
    doThrow(new NoSuchElementException()).when(service).eliminar(999);

    ResponseEntity<Void> resp = controller.eliminar(999);

    assertEquals(HttpStatus.NOT_FOUND, resp.getStatusCode());
  }

  @Test
  void eliminar_errorInterno() {
    doThrow(new RuntimeException()).when(service).eliminar(1);

    ResponseEntity<Void> resp = controller.eliminar(1);

    assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, resp.getStatusCode());
  }
}