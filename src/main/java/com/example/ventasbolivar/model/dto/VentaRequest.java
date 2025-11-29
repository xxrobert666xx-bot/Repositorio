package com.example.ventasbolivar.model.dto;

import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * VentaRequest.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VentaRequest {
  private Integer idCliente;
  private Integer idUsuario;
  private String tipoComprobante;
  private String serieComprobante;
  private String numComprobante;
  private LocalDateTime fechaHora;
  private Double impuesto;
  private Double totalVenta;
  private List<DetalleVentaRequest> detalles;
}