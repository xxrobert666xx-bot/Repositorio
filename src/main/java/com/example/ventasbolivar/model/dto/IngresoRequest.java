package com.example.ventasbolivar.model.dto;

import java.math.BigDecimal;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * IngresoRequest.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class IngresoRequest {
  private Integer idproveedor;
  private Integer idusuario;

  private String tipoComprobante;
  private String serieComprobante;
  private String numComprobante;

  private BigDecimal impuesto;
  private BigDecimal totalcompra;

  private List<DetalleIngresoRequest> detalles;
}
