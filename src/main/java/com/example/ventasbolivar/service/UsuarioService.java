package com.example.ventasbolivar.service;

import com.example.ventasbolivar.model.dto.UsuarioRequest;
import com.example.ventasbolivar.model.dto.UsuarioResponse;
import java.util.List;

/**
 * UsuarioService.
 */
public interface UsuarioService {
  /**
   * Listar usuarios.
   */
  List<UsuarioResponse> listar();

  /**
   * Obtener usuarios.
   */
  UsuarioResponse obtener(Integer id);

  /**
   * Crear usuarios.
   */
  UsuarioResponse crear(UsuarioRequest request);

  /**
   * Actualizar usuarios.
   */
  UsuarioResponse actualizar(Integer id, UsuarioRequest request);

  /**
   * Eliminar usuarios.
   */
  void eliminar(Integer id);
}