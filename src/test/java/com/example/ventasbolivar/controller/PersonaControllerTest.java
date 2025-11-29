package com.example.ventasbolivar.controller;

import com.example.ventasbolivar.model.dto.PersonaRequest;
import com.example.ventasbolivar.model.dto.PersonaResponse;
import com.example.ventasbolivar.service.PersonaService;
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
class PersonaControllerTest {

  @Mock
  private PersonaService service;

  @InjectMocks
  private PersonaController controller;

  private PersonaResponse response;
  private PersonaRequest request;

  @BeforeEach
  void setup() {
    response = new PersonaResponse(
     1,
     "Juan Perez",
     "Cliente",
     "12345678",
     "987654321"
    );

    request = new PersonaRequest();
    request.setNombre("Juan Perez");
    request.setTipoPersona("Cliente");
  }

  @Test
  void listar() {
    when(service.listar()).thenReturn(List.of(response));

    ResponseEntity<List<PersonaResponse>> resp = controller.listar();

    assertEquals(HttpStatus.OK, resp.getStatusCode());
    assertEquals(1, resp.getBody().size());
  }

  @Test
  void obtener() {
    when(service.obtener(1)).thenReturn(response);

    ResponseEntity<PersonaResponse> resp = controller.obtener(1);

    assertEquals(HttpStatus.OK, resp.getStatusCode());
    assertEquals("Juan Perez", resp.getBody().getNombre());
  }

  @Test
  void crear() {
    when(service.crear(request)).thenReturn(response);

    ResponseEntity<PersonaResponse> resp = controller.crear(request);

    assertEquals(HttpStatus.CREATED, resp.getStatusCode());
    assertEquals("Juan Perez", resp.getBody().getNombre());
  }

  @Test
  void actualizar() {
    when(service.actualizar(1, request)).thenReturn(response);

    ResponseEntity<PersonaResponse> resp = controller.actualizar(1, request);

    assertEquals(HttpStatus.OK, resp.getStatusCode());
    assertEquals("Cliente", resp.getBody().getTipoPersona());
  }

  @Test
  void eliminar() {
    ResponseEntity<Void> resp = controller.eliminar(1);

    assertEquals(HttpStatus.NO_CONTENT, resp.getStatusCode());
    verify(service).eliminar(1);
  }

  @Test
  void listarClientes() {
    when(service.listarClientes()).thenReturn(List.of(response));

    ResponseEntity<List<PersonaResponse>> resp = controller.listarClientes();

    assertEquals(HttpStatus.OK, resp.getStatusCode());
    assertEquals(1, resp.getBody().size());
  }

  @Test
  void listarProveedores() {
    PersonaResponse proveedor = new PersonaResponse(
     2,
     "Proveedor SAC",
     "Proveedor",
     "98765432",
     "912345678"
    );

    when(service.listarProveedores()).thenReturn(List.of(proveedor));

    ResponseEntity<List<PersonaResponse>> resp = controller.listarProveedores();

    assertEquals(HttpStatus.OK, resp.getStatusCode());
    assertEquals(1, resp.getBody().size());
    assertEquals("Proveedor", resp.getBody().get(0).getTipoPersona());
  }
}
