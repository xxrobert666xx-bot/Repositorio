package com.example.ventasbolivar.service;

import com.example.ventasbolivar.model.dto.PersonaRequest;
import com.example.ventasbolivar.model.dto.PersonaResponse;
import java.util.List;

/**
 * PersonaService.
 */
public interface PersonaService {
  /**
   * Listar personas.
   */
  List<PersonaResponse> listar();

  /**
   * Obtener personas.
   */
  PersonaResponse obtener(Integer id);

  /**
   * Crear personas.
   */
  PersonaResponse crear(PersonaRequest request);

  /**
   * Actualizar personas.
   */
  PersonaResponse actualizar(Integer id, PersonaRequest request);

  /**
   * Eliminar personas.
   */
  void eliminar(Integer id);

  /**
   * Listar clientes.
   */
  List<PersonaResponse> listarClientes();

  /**
   * Listar proveedores.
   */
  List<PersonaResponse> listarProveedores();

}