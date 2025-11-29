package com.example.ventasbolivar.controller;

import com.example.ventasbolivar.model.dto.PermisoResponse;
import com.example.ventasbolivar.service.PermisoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * PermisoController.
 */
@RestController
@RequestMapping("/api/permisos")
@RequiredArgsConstructor
@Tag(name = "Permisos", description = "Listado de permisos")
public class PermisoController {

  private final PermisoService service;

  /**
   * Listar.
   */
  @Operation(summary = "Listar todos los permisos")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "Lista devuelta correctamente"),
    @ApiResponse(responseCode = "500", description = "Error interno del servidor")
  })
  @GetMapping
  public ResponseEntity<List<PermisoResponse>> listar() {
    try {
      return ResponseEntity.ok(service.listar());
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }
}