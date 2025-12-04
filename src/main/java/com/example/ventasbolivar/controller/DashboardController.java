package com.example.ventasbolivar.controller;

import com.example.ventasbolivar.service.DashboardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * DashboardController.
 */
@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
@Tag(name = "Reportes", description = "Gestión de reportes del sistema")
public class DashboardController {
  private final DashboardService dashboardService;

  /**
   * Endpoint.
   */
  @Operation(
      summary = "Obtener datos del dashboard",
      description = "Devuelve estadísticas generales del sistema "
       + "(ventas, compras, productos, clientes, etc.) filtradas por un rango de fechas."
  )
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "Datos obtenidos correctamente"),
    @ApiResponse(responseCode = "400", description = "Parámetros de fecha inválidos"),
    @ApiResponse(responseCode = "500", description = "Error interno del servidor")
  })
  @GetMapping
  public ResponseEntity<?> getDashboardData(
      @Parameter(description = "Fecha de inicio del rango (formato: yyyy-MM-dd)",
        example = "2024-01-01")
      @RequestParam String inicio,
      @Parameter(description = "Fecha de fin del rango (formato: yyyy-MM-dd)",
        example = "2024-01-31")
      @RequestParam String fin
  ) {
    return ResponseEntity.ok(dashboardService.getDashboardData(inicio, fin));
  }
}
