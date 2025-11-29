package com.example.ventasbolivar.service;

import com.example.ventasbolivar.model.dto.PermisoResponse;
import java.util.List;

/**
 * PermisoService.
 */
public interface PermisoService {
  /**
   * Listar permisos.
   */
  List<PermisoResponse> listar();
}