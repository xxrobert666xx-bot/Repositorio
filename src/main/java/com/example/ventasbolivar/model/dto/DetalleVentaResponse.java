package com.example.ventasbolivar.model.dto;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DetalleVentaResponse.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetalleVentaResponse {
  private Integer id;
  private Integer idArticulo;
  private String nombreArticulo;
  private Integer cantidad;
  private BigDecimal precioVenta;
  private BigDecimal descuento;
}