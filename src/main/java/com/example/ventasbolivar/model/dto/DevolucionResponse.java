package com.example.ventasbolivar.model.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DevolucionResponse.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DevolucionResponse {
  private Integer iddevolucion;
  private Integer idventa;
  private Integer idarticulo;
  private Integer cantidad;
  private String motivo;
  private LocalDateTime fechaDevolucion;
}
