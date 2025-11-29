package com.example.ventasbolivar.controller;

import com.example.ventasbolivar.model.dto.UsuarioRequest;
import com.example.ventasbolivar.model.dto.UsuarioResponse;
import com.example.ventasbolivar.service.UsuarioService;
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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UsuarioControllerTest {

  @Mock
  private UsuarioService service;

  @InjectMocks
  private UsuarioController controller;

  private UsuarioResponse response;
  private UsuarioRequest request;

  @BeforeEach
  void setup() {
    response = new UsuarioResponse(
     1,
     "Juan Perez",
     "juan",
     "Admin",
     1
    );

    request = new UsuarioRequest();
    request.setNombre("Juan Perez");
    request.setLogin("juan");
  }

  @Test
  void listar() {
    when(service.listar()).thenReturn(List.of(response));

    ResponseEntity<List<UsuarioResponse>> resp = controller.listar();

    assertEquals(HttpStatus.OK, resp.getStatusCode());
    assertEquals(1, resp.getBody().size());
  }

  @Test
  void obtener() {
    when(service.obtener(1)).thenReturn(response);

    ResponseEntity<UsuarioResponse> resp = controller.obtener(1);

    assertEquals(HttpStatus.OK, resp.getStatusCode());
    assertEquals("juan", resp.getBody().getLogin());
  }

  @Test
  void crear() {
    when(service.crear(request)).thenReturn(response);

    ResponseEntity<UsuarioResponse> resp = controller.crear(request);

    assertEquals(HttpStatus.CREATED, resp.getStatusCode());
    assertEquals("juan", resp.getBody().getLogin());
  }

  @Test
  void actualizar() {
    when(service.actualizar(1, request)).thenReturn(response);

    ResponseEntity<UsuarioResponse> resp = controller.actualizar(1, request);

    assertEquals(HttpStatus.OK, resp.getStatusCode());
    assertEquals("juan", resp.getBody().getLogin());
  }

  @Test
  void eliminar() {
    ResponseEntity<Void> resp = controller.eliminar(1);

    assertEquals(HttpStatus.NO_CONTENT, resp.getStatusCode());
    verify(service).eliminar(1);
  }
}
