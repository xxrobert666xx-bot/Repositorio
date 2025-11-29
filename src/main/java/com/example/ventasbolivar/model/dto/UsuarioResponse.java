package com.example.ventasbolivar.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * UsuarioResponse.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioResponse {
  private Integer id;
  private String nombre;
  private String login;
  private String cargo;
  private Integer condicion;
}