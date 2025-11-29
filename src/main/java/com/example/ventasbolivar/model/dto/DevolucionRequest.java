package com.example.ventasbolivar.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DevolucionRequest.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DevolucionRequest {
  private Integer idventa;
  private Integer idarticulo;
  private Integer cantidad;
  private String motivo;
}
