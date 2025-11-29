package com.example.ventasbolivar.model.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * IngresoResponse.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class IngresoResponse {
  private Integer idingreso;
  private Integer idproveedor;
  private Integer idusuario;

  private String tipoComprobante;
  private String serieComprobante;
  private String numComprobante;

  private LocalDateTime fechahora;
  private BigDecimal impuesto;
  private BigDecimal totalcompra;
  private String estado;

  private List<DetalleIngresoResponse> detalles;
}
