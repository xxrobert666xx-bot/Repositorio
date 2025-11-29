package com.example.ventasbolivar.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ArticuloResponse.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticuloResponse {
  private Integer id;
  private String nombre;
  private String codigo;
  private Integer stock;
  private Double precioVenta;

  private Integer idCategoria;      // para el select al editar
  private String nombreCategoria;
}
