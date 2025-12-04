package com.example.ventasbolivar.controller;

import com.example.ventasbolivar.model.dto.ArticuloVentaResponse;
import com.example.ventasbolivar.model.dto.DetalleVentaResponse;
import com.example.ventasbolivar.model.dto.SelectOptionDto;
import com.example.ventasbolivar.model.dto.SerieNumeroResponse;
import com.example.ventasbolivar.model.dto.VentaRequest;
import com.example.ventasbolivar.model.dto.VentaResponse;
import com.example.ventasbolivar.service.VentaService;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * VentaController.
 */
@RestController
@RequestMapping("/api/ventas")
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
@Tag(name = "Ventas", description = "Gestión de ventas del sistema")
public class VentaController {

  private final VentaService service;

  /**
   * Endpoint.
   */
  @Operation(summary = "Listar todas las ventas")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "Lista devuelta"),
    @ApiResponse(responseCode = "500", description = "Error interno")
  })
  @GetMapping
    public ResponseEntity<List<VentaResponse>> listar() {
    return ResponseEntity.ok(service.listar());
  }

  /**
   * Endpoint.
   */
  @Operation(summary = "Obtener venta por ID")
  @GetMapping("/{id}")
 public ResponseEntity<VentaResponse> obtener(
      @Parameter(description = "ID venta", example = "1")
      @PathVariable Integer id) {
    try {
      return ResponseEntity.ok(service.obtener(id));
    } catch (NoSuchElementException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
  }

  /**
   * Endpoint.
   */
  @Operation(summary = "Crear una nueva venta")
  @PostMapping
 public ResponseEntity<VentaResponse> crear(@RequestBody VentaRequest req) {
    try {
      VentaResponse resp = service.crear(req);
      return ResponseEntity.status(HttpStatus.CREATED).body(resp);
    } catch (IllegalArgumentException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    } catch (NoSuchElementException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

  /**
   * Endpoint.
   */
  @Operation(summary = "Anular una venta")
  @DeleteMapping("/{id}")
 public ResponseEntity<Void> anular(
      @Parameter(description = "ID venta", example = "1")
      @PathVariable Integer id) {
    try {
      service.anular(id);
      return ResponseEntity.noContent().build();
    } catch (NoSuchElementException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
  }

  /**
   * Endpoint.
   */
  @Operation(summary = "selectCliente - listar clientes (id, nombre)")
  @GetMapping("/selectCliente")
 public ResponseEntity<List<SelectOptionDto>> selectCliente() {
    return ResponseEntity.ok(service.selectClientes());
  }

  /**
   * Endpoint.
   */
  @Operation(summary = "listarArticulos - artículos activos para venta (con descuento aplicado)")
  @GetMapping("/listarArticulos")
 public ResponseEntity<List<ArticuloVentaResponse>> listarArticulos() {
    return ResponseEntity.ok(service.listarArticulosActivosVenta());
  }

  /**
   * Endpoint.
   */
  @Operation(summary = "nextSerieNumero - obtiene serie y siguiente número")
  @GetMapping("/nextSerieNumero")
 public ResponseEntity<SerieNumeroResponse> nextSerieNumero(
      @Parameter(description = "Tipo de documento",
       example = "Boleta, factura o ticket") String tipo) {
    return ResponseEntity.ok(service.nextSerieNumero(tipo));
  }

  /**
   * Endpoint.
   */
  @Operation(summary = "listarDetalle - detalles de una venta")
  @GetMapping("/{id}/detalles")
 public ResponseEntity<List<DetalleVentaResponse>> listarDetalle(
      @Parameter(description = "ID venta", example = "1")
      @PathVariable Integer id) {
    return ResponseEntity.ok(service.listarDetalle(id));
  }
}
