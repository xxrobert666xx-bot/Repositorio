package com.example.ventasbolivar.controller;

import com.example.ventasbolivar.model.dto.PermisoResponse;
import com.example.ventasbolivar.service.PermisoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PermisoControllerTest {

  @Mock
  private PermisoService service;

  @InjectMocks
  private PermisoController controller;

  @Test
  void listar() {
    when(service.listar()).thenReturn(List.of(
     new PermisoResponse(1, "Compras"),
     new PermisoResponse(2, "Ventas")
    ));

    ResponseEntity<List<PermisoResponse>> resp = controller.listar();

    assertEquals(200, resp.getStatusCodeValue());
    assertNotNull(resp.getBody());
    assertEquals(2, resp.getBody().size());
    assertEquals("Compras", resp.getBody().get(0).getNombre());
  }

}
