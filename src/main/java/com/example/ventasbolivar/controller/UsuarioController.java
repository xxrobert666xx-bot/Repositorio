package com.example.ventasbolivar.controller;

import com.example.ventasbolivar.model.dto.UsuarioRequest;
import com.example.ventasbolivar.model.dto.UsuarioResponse;
import com.example.ventasbolivar.service.UsuarioService;
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
 * UsuarioController.
 */
@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
@Tag(name = "Usuarios", description = "Gestión de usuarios del sistema")
public class UsuarioController {

  private final UsuarioService service;

  /**
   * Endpoint.
   */
  @Operation(summary = "Listar todos los usuarios")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "Lista de usuarios devuelta exitosamente"),
    @ApiResponse(responseCode = "500", description = "Error interno del servidor")
  })
  @GetMapping
  public ResponseEntity<List<UsuarioResponse>> listar() {
    try {
      return ResponseEntity.ok(service.listar());
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

  /**
   * Endpoint.
   */
  @Operation(summary = "Obtener un usuario por ID")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "Usuario encontrado"),
    @ApiResponse(responseCode = "404", description = "Usuario no encontrado"),
    @ApiResponse(responseCode = "500", description = "Error interno del servidor")
  })
  @GetMapping("/{id}")
  public ResponseEntity<UsuarioResponse> obtener(
      @Parameter(description = "ID del usuario a buscar", example = "1")
      @PathVariable Integer id
  ) {
    try {
      UsuarioResponse resp = service.obtener(id);
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
  @Operation(summary = "Crear un nuevo usuario")
  @ApiResponses({
    @ApiResponse(responseCode = "201", description = "Usuario creado exitosamente"),
    @ApiResponse(responseCode = "400", description = "Datos inválidos"),
    @ApiResponse(responseCode = "500", description = "Error interno del servidor")
  })
  @PostMapping
  public ResponseEntity<UsuarioResponse> crear(
      @Parameter(description = "Datos del nuevo usuario")
      @RequestBody UsuarioRequest req
  ) {
    try {
      UsuarioResponse resp = service.crear(req);
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
  @Operation(summary = "Actualizar un usuario existente")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "Usuario actualizado correctamente"),
    @ApiResponse(responseCode = "400", description = "Datos inválidos"),
    @ApiResponse(responseCode = "404", description = "Usuario no encontrado"),
    @ApiResponse(responseCode = "500", description = "Error interno del servidor")
  })
  @PutMapping("/{id}")
  public ResponseEntity<UsuarioResponse> actualizar(
      @Parameter(description = "ID del usuario a actualizar", example = "1")
      @PathVariable Integer id,
      @Parameter(description = "Datos actualizados del usuario")
      @RequestBody UsuarioRequest req
  ) {
    try {
      UsuarioResponse resp = service.actualizar(id, req);
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
  @Operation(summary = "Eliminar un usuario por ID")
  @ApiResponses({
    @ApiResponse(responseCode = "204", description = "Usuario eliminado"),
    @ApiResponse(responseCode = "404", description = "Usuario no encontrado"),
    @ApiResponse(responseCode = "500", description = "Error interno del servidor")
  })
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> eliminar(
      @Parameter(description = "ID del usuario a eliminar", example = "1")
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
