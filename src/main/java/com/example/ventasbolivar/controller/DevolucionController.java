package com.example.ventasbolivar.controller;

import com.example.ventasbolivar.model.dto.DevolucionRequest;
import com.example.ventasbolivar.model.dto.DevolucionResponse;
import com.example.ventasbolivar.service.DevolucionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * DevolucionController.
 */
@RestController
@RequestMapping("/api/devoluciones")
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
@Tag(name = "Devoluciones", description = "Gestión de devoluciones del sistema")
public class DevolucionController {
  private final DevolucionService devolucionService;

  /**
   * DevolucionController.
   */
  public DevolucionController(DevolucionService devolucionService) {
    this.devolucionService = devolucionService;
  }

  /**
   * Endpoint.
   */
  @Operation(
      summary = "Registrar una devolución",
      description = "Registra una nueva devolución asociada a una venta y sus detalles."
  )
  @ApiResponses({
    @ApiResponse(responseCode = "201", description = "Devolución registrada correctamente"),
    @ApiResponse(responseCode = "400", description = "Datos inválidos en la solicitud"),
    @ApiResponse(responseCode = "404", description = "Venta o detalle no encontrado"),
    @ApiResponse(responseCode = "500", description = "Error interno del servidor")
  })
  @PostMapping
  public DevolucionResponse registrar(
      @Parameter(description = "Datos de la devolución a registrar")
      @RequestBody DevolucionRequest request) {
    return devolucionService.registrarDevolucion(request);
  }

  /**
   * Endpoint.
   */
  @Operation(
      summary = "Listar devoluciones",
      description = "Devuelve todas las devoluciones registradas en el sistema."
  )
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente")
  })
  @GetMapping
  public List<DevolucionResponse> listar() {
    return devolucionService.listarDevoluciones();
  }

  /**
   * Endpoint.
   */
  @Operation(
      summary = "Obtener devolución por ID",
      description = "Devuelve los datos de una devolución específica usando su identificador."
  )
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "Devolución encontrada"),
    @ApiResponse(responseCode = "404", description = "Devolución no encontrada")
  })
  @GetMapping("/{id}")
  public DevolucionResponse obtener(
      @Parameter(description = "ID de la devolución", example = "1")
      @PathVariable Integer id) {
    return devolucionService.obtenerPorId(id);
  }

}
