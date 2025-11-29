package com.example.ventasbolivar.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * CategoriaRequest.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaRequest {
  private String nombre;
  private String descripcion;
  private Integer condicion;
}
