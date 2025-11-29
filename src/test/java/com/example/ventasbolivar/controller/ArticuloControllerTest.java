package com.example.ventasbolivar.controller;

import com.example.ventasbolivar.model.dto.ArticuloRequest;
import com.example.ventasbolivar.model.dto.ArticuloResponse;
import com.example.ventasbolivar.service.ArticuloService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ArticuloControllerTest {

  @Mock
  private ArticuloService service;

  @InjectMocks
  private ArticuloController controller;

  private ArticuloResponse response;
  private ArticuloRequest request;

  @BeforeEach
  void setup() {
    response = new ArticuloResponse(
     1,
     "Laptop",
     "L001",
     10,
     3500.0,
     2,
     "Tecnología"
    );

    request = new ArticuloRequest();
    request.setIdCategoria(2);
    request.setNombre("Laptop");
    request.setCodigo("L001");
    request.setStock(10);
    request.setPrecioVenta(3500.0);
  }

  @Test
  void listar() {
    when(service.listar()).thenReturn(List.of(response));

    ResponseEntity<List<ArticuloResponse>> resp = controller.listar();

    assertEquals(HttpStatus.OK, resp.getStatusCode());
    assertNotNull(resp.getBody());
    assertEquals(1, resp.getBody().size());
  }

  @Test
  void obtener() {
    when(service.obtener(1)).thenReturn(response);

    ResponseEntity<ArticuloResponse> resp = controller.obtener(1);

    assertEquals(HttpStatus.OK, resp.getStatusCode());
    assertEquals("Laptop", resp.getBody().getNombre());
    assertEquals("Tecnología", resp.getBody().getNombreCategoria());
  }

  @Test
  void crear() {
    when(service.crear(request)).thenReturn(response);

    ResponseEntity<ArticuloResponse> resp = controller.crear(request);

    assertEquals(HttpStatus.CREATED, resp.getStatusCode());
    assertEquals("Laptop", resp.getBody().getNombre());
    assertEquals(2, resp.getBody().getIdCategoria());
  }

  @Test
  void actualizar() {
    when(service.actualizar(1, request)).thenReturn(response);

    ResponseEntity<ArticuloResponse> resp = controller.actualizar(1, request);

    assertEquals(HttpStatus.OK, resp.getStatusCode());
    assertEquals("Tecnología", resp.getBody().getNombreCategoria());
  }

  @Test
  void eliminar() {
    ResponseEntity<Void> resp = controller.eliminar(1);

    assertEquals(HttpStatus.NO_CONTENT, resp.getStatusCode());
    verify(service).eliminar(1);
  }
}