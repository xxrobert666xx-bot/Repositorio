package com.example.ventasbolivar.service;

import com.example.ventasbolivar.model.dto.DevolucionRequest;
import com.example.ventasbolivar.model.dto.DevolucionResponse;
import java.util.List;

/**
 * DevolucionService.
 */
public interface DevolucionService {
  /**
   * Registrar devolucion.
   */
  DevolucionResponse registrarDevolucion(DevolucionRequest request);

  /**
   * Listar devolucion.
   */
  List<DevolucionResponse> listarDevoluciones();

  /**
   * Obtener devolucion.
   */
  DevolucionResponse obtenerPorId(Integer id);
}
