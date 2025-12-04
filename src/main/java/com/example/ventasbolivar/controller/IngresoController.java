package com.example.ventasbolivar.controller;

import com.example.ventasbolivar.model.dto.IngresoRequest;
import com.example.ventasbolivar.model.dto.IngresoResponse;
import com.example.ventasbolivar.service.IngresoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * IngresoController.
 */
@RestController
@RequestMapping("/api/ingresos")
@RequiredArgsConstructor
@Tag(name = "Ingresos", description = "Gestión de ingresos del sistema")
public class IngresoController {

  private final IngresoService ingresoService;

  /**
   * Endpoint.
   */
  @Operation(summary = "Listar todos los ingresos")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "Lista de ingresos devuelta exitosamente"),
    @ApiResponse(responseCode = "500", description = "Error interno del servidor")
  })
  @GetMapping
 public ResponseEntity<List<IngresoResponse>> listar() {
    try {
      return ResponseEntity.ok(ingresoService.listar());
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

  /**
   * Obtener ingreso.
   */
  @Operation(summary = "Obtener un ingreso por ID")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "Ingreso encontrado"),
    @ApiResponse(responseCode = "404", description = "Ingreso no encontrado"),
    @ApiResponse(responseCode = "500", description = "Error interno del servidor")
  })
  @GetMapping("/{id}")
 public ResponseEntity<IngresoResponse> obtener(
      @Parameter(description = "ID del ingreso a buscar", example = "1")
      @PathVariable Integer id
  ) {
    try {
      IngresoResponse resp = ingresoService.obtenerPorId(id);
      return ResponseEntity.ok(resp);
    } catch (NoSuchElementException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

  /**
   * Registrar un ingreso.
   */
  @Operation(summary = "Registrar un nuevo ingreso")
  @ApiResponses({
    @ApiResponse(responseCode = "201", description = "Ingreso registrado exitosamente"),
    @ApiResponse(responseCode = "400", description = "Datos inválidos"),
    @ApiResponse(responseCode = "500", description = "Error interno del servidor")
  })
  @PostMapping
 public ResponseEntity<IngresoResponse> registrar(
      @Parameter(description = "Datos del nuevo ingreso")
      @RequestBody IngresoRequest request
  ) {
    try {
      IngresoResponse resp = ingresoService.registrarIngreso(request);
      return ResponseEntity.status(HttpStatus.CREATED).body(resp);
    } catch (IllegalArgumentException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

  /**
   * Anular venta.
   */
  @Operation(summary = "Anular un ingreso existente")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "Ingreso anulado correctamente"),
    @ApiResponse(responseCode = "404", description = "Ingreso no encontrado"),
    @ApiResponse(responseCode = "500", description = "Error interno del servidor")
  })
  @PutMapping("/anular/{id}")
 public ResponseEntity<IngresoResponse> anular(
       @Parameter(description = "ID del ingreso a anular", example = "1")
       @PathVariable Integer id
  ) {
    try {
      IngresoResponse resp = ingresoService.anularIngreso(id);
      return ResponseEntity.ok(resp);
    } catch (NoSuchElementException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }
}
