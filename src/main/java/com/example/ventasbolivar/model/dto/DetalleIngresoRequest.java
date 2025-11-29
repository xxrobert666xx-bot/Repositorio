package com.example.ventasbolivar.model.dto;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DetalleIngresoRequest.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetalleIngresoRequest {
  private Integer idarticulo;
  private Integer cantidad;
  private BigDecimal precioCompra;
  private BigDecimal precioVenta;
}
