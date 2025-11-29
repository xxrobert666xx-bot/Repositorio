package com.example.ventasbolivar.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ArticuloRequest.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor

public class ArticuloRequest {
  private Integer idCategoria;
  private String codigo;
  private String nombre;
  private Integer stock;
  private Double precioVenta;
  private String descripcion;
  private String imagen;
  private Integer condicion;
  private Double precioCompra;
}
