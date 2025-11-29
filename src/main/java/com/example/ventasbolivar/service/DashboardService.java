package com.example.ventasbolivar.service;

import java.util.Map;

/**
 * DashboardService.
 */
public interface DashboardService {
  /**
   * Obtener datos para el reporte.
   */
  Map<String, Object> getDashboardData(String inicio, String fin);
}
