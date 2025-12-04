package com.example.ventasbolivar.controller;

import com.example.ventasbolivar.model.dto.ArticuloRequest;
import com.example.ventasbolivar.model.dto.ArticuloResponse;
import com.example.ventasbolivar.service.ArticuloService;
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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * ArticuloController.
 */
@RestController
@RequestMapping("/api/articulos")
@RequiredArgsConstructor
@CrossOrigin(
    origins = "http://localhost:5173",
    methods = {
        RequestMethod.GET,
        RequestMethod.POST,
        RequestMethod.PUT,
        RequestMethod.DELETE,
        RequestMethod.OPTIONS
    },
    allowedHeaders = "*"
)
@Tag(name = "Artículos", description = "Gestión de artículos del sistema")
public class ArticuloController {

  private final ArticuloService service;

  /**
   * Endpoint.
   */
  @Operation(
      summary = "Listar todos los artículos",
      description = "Devuelve un listado completo de los artículos registrados."
  )
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "Listado obtenido correctamente"),
    @ApiResponse(responseCode = "500", description = "Error interno del servidor")
  })
  @GetMapping
  public ResponseEntity<List<ArticuloResponse>> listar() {
    try {
      return ResponseEntity.ok(service.listar());
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

  /**
   * Endpoint.
   */
  @Operation(
      summary = "Obtener un artículo por ID",
      description = "Devuelve un artículo a partir de su ID."
  )
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "Artículo encontrado"),
    @ApiResponse(responseCode = "404", description = "Artículo no encontrado"),
    @ApiResponse(responseCode = "500", description = "Error interno del servidor")
  })
  @GetMapping("/{id}")
  public ResponseEntity<ArticuloResponse> obtener(
      @Parameter(description = "ID del artículo", example = "1")
      @PathVariable Integer id) {
    try {
      ArticuloResponse resp = service.obtener(id);
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
  @Operation(
      summary = "Registrar artículo",
      description = "Crea un nuevo artículo en el sistema."
  )
  @ApiResponses({
    @ApiResponse(responseCode = "201", description = "Artículo creado correctamente"),
    @ApiResponse(responseCode = "400", description = "Datos inválidos"),
    @ApiResponse(responseCode = "500", description = "Error interno del servidor")
  })
  @PostMapping
  public ResponseEntity<ArticuloResponse> crear(
      @io.swagger.v3.oas.annotations.parameters.RequestBody(
      description = "Datos del artículo a registrar"
   )
      @RequestBody ArticuloRequest req) {
    try {
      ArticuloResponse resp = service.crear(req);
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
  @Operation(
      summary = "Actualizar artículo",
      description = "Modifica los datos de un artículo existente."
  )
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "Artículo actualizado correctamente"),
    @ApiResponse(responseCode = "404", description = "Artículo no encontrado"),
    @ApiResponse(responseCode = "400", description = "Datos inválidos"),
    @ApiResponse(responseCode = "500", description = "Error interno del servidor")
  })
  @PutMapping("/{id}")
  public ResponseEntity<ArticuloResponse> actualizar(
      @Parameter(description = "ID del artículo a actualizar", example = "1")
      @PathVariable Integer id,
      @io.swagger.v3.oas.annotations.parameters.RequestBody(
      description = "Datos actualizados del artículo"
   )
      @RequestBody ArticuloRequest req) {
    try {
      ArticuloResponse resp = service.actualizar(id, req);
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
  @Operation(
      summary = "Eliminar artículo",
      description = "Elimina un artículo según su ID."
  )
  @ApiResponses({
    @ApiResponse(responseCode = "204", description = "Artículo eliminado correctamente"),
    @ApiResponse(responseCode = "404", description = "Artículo no encontrado"),
    @ApiResponse(responseCode = "500", description = "Error interno del servidor")
  })
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> eliminar(
      @Parameter(description = "ID del artículo a eliminar", example = "1")
      @PathVariable Integer id) {
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
