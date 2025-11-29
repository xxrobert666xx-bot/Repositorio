package com.example.ventasbolivar.controller;

import com.example.ventasbolivar.service.DashboardService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DashboardControllerTest {

  @InjectMocks
  private DashboardController dashboardController;

  @Mock
  private DashboardService dashboardService;

  private Map<String, Object> mockDashboard;

  private String inicio;
  private String fin;

  @BeforeEach
  void setup() {
    inicio = "2025-01-01";
    fin = "2025-01-10";

    mockDashboard = new HashMap<>();
    mockDashboard.put("kpis", Map.of("ventas_totales", 1000.0));
    mockDashboard.put("rendimientoVentas", List.of(Map.of("dia", "2025-01-01", "total", 100)));
    mockDashboard.put("ventasPorCategoria", List.of(Map.of("categoria", "Bebidas", "total", 300)));
    mockDashboard.put("topClientes", List.of(Map.of("cliente", "Juan", "total", 150)));
    mockDashboard.put("rankingRentabilidad", List.of(Map.of("articulo", "Pizza", "ganancia", 200)));
    mockDashboard.put("mapaCalorVentas", List.of(Map.of("hora", "10:00", "total", 50)));
    mockDashboard.put("resumenSistema", Map.of("usuarios", 20, "articulos", 100));
  }

  @Test
  void getDashboardData_Correctamente() {
    // Mockear service
    when(dashboardService.getDashboardData(inicio, fin)).thenReturn(mockDashboard);

    // Ejecutar
    ResponseEntity<?> response = dashboardController.getDashboardData(inicio, fin);

    // Validaciones
    assertNotNull(response);
    assertEquals(200, response.getStatusCodeValue());
    assertEquals(mockDashboard, response.getBody());

    // Verificar que service fue llamado correctamente
    verify(dashboardService, times(1)).getDashboardData(inicio, fin);
  }
}