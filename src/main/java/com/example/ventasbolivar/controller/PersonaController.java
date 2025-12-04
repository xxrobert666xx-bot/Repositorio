package com.example.ventasbolivar.controller;

import com.example.ventasbolivar.model.dto.PersonaRequest;
import com.example.ventasbolivar.model.dto.PersonaResponse;
import com.example.ventasbolivar.service.PersonaService;
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
 * PersonaController.
 */
@RestController
@RequestMapping("/api/personas")
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
@Tag(name = "Personas", description = "Gestión de personas (clientes y proveedores) del sistema")
public class PersonaController {

  private final PersonaService service;

  /**
   * Endpoint.
   */
  @Operation(
      summary = "Listar todas las personas",
      description = "Devuelve un listado completo de las personas registrados."
  )
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente"),
    @ApiResponse(responseCode = "500", description = "Error interno del servidor")
  })
  @GetMapping
  public ResponseEntity<List<PersonaResponse>> listar() {
    return ResponseEntity.ok(service.listar());
  }

  /**
   * Endpoint.
   */
  @Operation(summary = "Obtener una persona por ID")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "Persona encontrada"),
    @ApiResponse(responseCode = "404", description = "Persona no encontrada")
  })
  @GetMapping("/{id}")
  public ResponseEntity<PersonaResponse> obtener(
      @Parameter(description = "ID de la persona", example = "1")
      @PathVariable Integer id
  ) {
    try {
      return ResponseEntity.ok(service.obtener(id));
    } catch (NoSuchElementException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
  }

  /**
   * Endpoint.
   */
  @Operation(summary = "Crear una nueva persona")
  @ApiResponses({
    @ApiResponse(responseCode = "201", description = "Persona creada exitosamente"),
    @ApiResponse(responseCode = "400", description = "Datos inválidos")
  })
  @PostMapping
  public ResponseEntity<PersonaResponse> crear(
      @Parameter(description = "Datos de la persona a registrar")
      @RequestBody PersonaRequest req
  ) {
    try {
      return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(req));
    } catch (IllegalArgumentException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
  }

  /**
   * Endpoint.
   */
  @Operation(summary = "Actualizar una persona por ID")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "Persona actualizada correctamente"),
    @ApiResponse(responseCode = "404", description = "Persona no encontrada")
  })
  @PutMapping("/{id}")
  public ResponseEntity<PersonaResponse> actualizar(
      @Parameter(description = "ID de la persona", example = "1")
      @PathVariable Integer id,
      @RequestBody PersonaRequest req
  ) {
    try {
      return ResponseEntity.ok(service.actualizar(id, req));
    } catch (NoSuchElementException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
  }

  /**
   * Endpoint.
   */
  @Operation(summary = "Eliminar una persona por ID")
  @ApiResponses({
    @ApiResponse(responseCode = "204", description = "Persona eliminada correctamente"),
    @ApiResponse(responseCode = "404", description = "Persona no encontrada")
  })
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> eliminar(
      @Parameter(description = "ID de la persona", example = "1")
      @PathVariable Integer id
  ) {
    try {
      service.eliminar(id);
      return ResponseEntity.noContent().build();
    } catch (NoSuchElementException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
  }

  /**
   * Endpoint.
   */
  @Operation(summary = "Listar solo clientes")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente")
  })
  @GetMapping("/clientes")
  public ResponseEntity<List<PersonaResponse>> listarClientes() {
    return ResponseEntity.ok(service.listarClientes());
  }

  /**
   * Endpoint.
   */
  @Operation(summary = "Listar solo proveedores")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente")
  })
  @GetMapping("/proveedores")
  public ResponseEntity<List<PersonaResponse>> listarProveedores() {
    return ResponseEntity.ok(service.listarProveedores());
  }
}

