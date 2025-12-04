package com.example.ventasbolivar.controller;

import com.example.ventasbolivar.model.dto.CategoriaRequest;
import com.example.ventasbolivar.model.dto.CategoriaResponse;
import com.example.ventasbolivar.service.CategoriaService;
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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * CategoriaController.
 */
@RestController
@RequestMapping("/api/categorias")
@RequiredArgsConstructor
@Tag(name = "Categorías", description = "Gestión de categorías del sistema")
public class CategoriaController {

  private final CategoriaService service;

  /**
   * Endpoint.
   */
  @Operation(summary = "Listar todas las categorías")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "Lista de categorías devuelta exitosamente"),
    @ApiResponse(responseCode = "500", description = "Error interno del servidor")
  })
  @GetMapping
  public ResponseEntity<List<CategoriaResponse>> listar() {
    try {
      return ResponseEntity.ok(service.listar());
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

  /**
   * Endpoint.
   */
  @Operation(summary = "Obtener una categoría por ID")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "Categoría encontrada"),
    @ApiResponse(responseCode = "404", description = "Categoría no encontrada"),
    @ApiResponse(responseCode = "500", description = "Error interno del servidor")
  })
  @GetMapping("/{id}")
  public ResponseEntity<CategoriaResponse> obtener(
      @Parameter(description = "ID de la categoría a buscar", example = "1")
      @PathVariable Integer id
  ) {
    try {
      CategoriaResponse resp = service.obtener(id);
      return ResponseEntity.ok(resp);
    } catch (NoSuchElementException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

  /**
   * Endpoint.
   */
  @Operation(summary = "Crear una nueva categoría")
  @ApiResponses({
    @ApiResponse(responseCode = "201", description = "Categoría creada exitosamente"),
    @ApiResponse(responseCode = "400", description = "Datos inválidos"),
    @ApiResponse(responseCode = "500", description = "Error interno del servidor")
  })
  @PostMapping
  public ResponseEntity<CategoriaResponse> crear(
      @Parameter(description = "Datos de la nueva categoría")
      @RequestBody CategoriaRequest req
  ) {
    try {
      CategoriaResponse resp = service.crear(req);
      return ResponseEntity.status(HttpStatus.CREATED).body(resp);
    } catch (IllegalArgumentException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

  /**
   * Endpoint.
   */
  @Operation(summary = "Actualizar una categoría existente")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "Categoría actualizada correctamente"),
    @ApiResponse(responseCode = "400", description = "Datos inválidos"),
    @ApiResponse(responseCode = "404", description = "Categoría no encontrada"),
    @ApiResponse(responseCode = "500", description = "Error interno del servidor")
  })
  @PutMapping("/{id}")
  public ResponseEntity<CategoriaResponse> actualizar(
      @Parameter(description = "ID de la categoría a actualizar", example = "1")
      @PathVariable Integer id,

      @Parameter(description = "Datos actualizados de la categoría")
      @RequestBody CategoriaRequest req
  ) {
    try {
      CategoriaResponse resp = service.actualizar(id, req);
      return ResponseEntity.ok(resp);
    } catch (NoSuchElementException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    } catch (IllegalArgumentException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

  /**
   * Endpoint.
   */
  @Operation(summary = "Eliminar una categoría por ID")
  @ApiResponses({
    @ApiResponse(responseCode = "204", description = "Categoría eliminada"),
    @ApiResponse(responseCode = "404", description = "Categoría no encontrada"),
    @ApiResponse(responseCode = "500", description = "Error interno del servidor")
  })
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> eliminar(
      @Parameter(description = "ID de la categoría a eliminar", example = "1")
      @PathVariable Integer id
  ) {
    try {
      service.eliminar(id);
      return ResponseEntity.noContent().build();
    } catch (NoSuchElementException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

}
