package com.example.ventasbolivar.service;

import com.example.ventasbolivar.model.dto.IngresoRequest;
import com.example.ventasbolivar.model.dto.IngresoResponse;
import java.util.List;

/**
 * IngresoService.
 */
public interface IngresoService {
  /**
   * Registrar ingreso.
   */
  IngresoResponse registrarIngreso(IngresoRequest request);

  /**
   * Listar ingreso.
   */
  List<IngresoResponse> listar();

  /**
   * Obtener ingreso.
   */
  IngresoResponse obtenerPorId(Integer id);

  /**
   * Anular ingreso.
   */
  IngresoResponse anularIngreso(Integer id);
}
