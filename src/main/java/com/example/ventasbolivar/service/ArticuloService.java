package com.example.ventasbolivar.service;

import com.example.ventasbolivar.model.dto.ArticuloRequest;
import com.example.ventasbolivar.model.dto.ArticuloResponse;
import java.util.List;

/**
 * ArticuloService.
 */
public interface ArticuloService {
  /**
   * Listar articulo.
   */
  List<ArticuloResponse> listar();

  /**
   * Obtener articulo.
   */
  ArticuloResponse obtener(Integer id);

  /**
   * Crear articulo.
   */
  ArticuloResponse crear(ArticuloRequest request);

  /**
   * Actualizar articulo.
   */
  ArticuloResponse actualizar(Integer id, ArticuloRequest request);

  /**
   * Eliminar articulo.
   */
  void eliminar(Integer id);
}
