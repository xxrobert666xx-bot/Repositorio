package com.example.ventasbolivar.service;

import com.example.ventasbolivar.model.dto.CategoriaRequest;
import com.example.ventasbolivar.model.dto.CategoriaResponse;
import java.util.List;

/**
 * CategoriaService.
 */
public interface CategoriaService {
  /**
   * Listar categoria.
   */
  List<CategoriaResponse> listar();

  /**
   * Obtener categoria.
   */
  CategoriaResponse obtener(Integer id);

  /**
   * Crear categoria.
   */
  CategoriaResponse crear(CategoriaRequest request);

  /**
   * Actualizar categoria.
   */
  CategoriaResponse actualizar(Integer id, CategoriaRequest request);

  /**
   * Eliminar categoria.
   */
  void eliminar(Integer id);
}
