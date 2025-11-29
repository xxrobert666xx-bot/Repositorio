package com.example.ventasbolivar.model.dto;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DetalleVentaRequest.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetalleVentaRequest {
  private Integer idArticulo;
  private Integer cantidad;
  private BigDecimal precioVenta;
  private BigDecimal descuento;
}
