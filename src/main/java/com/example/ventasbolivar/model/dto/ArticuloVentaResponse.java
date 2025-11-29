package com.example.ventasbolivar.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ArticuloVentaResponse.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticuloVentaResponse {
  private Integer idArticulo;
  private String nombre;
  private String categoria;
  private String codigo;
  private Integer stock;
  private Double precioVenta;
  private Double descuentoAplicado; // monto en S/. o 0
  private String imagen;
}
