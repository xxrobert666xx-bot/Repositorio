package com.example.ventasbolivar.model.dto;

import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * VentaResponse.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VentaResponse {
  private Integer id;
  private Integer idCliente;
  private String nombreCliente;
  private Integer idUsuario;
  private String tipoComprobante;
  private String serieComprobante;
  private String numComprobante;
  private LocalDateTime fechaHora;
  private Double impuesto;
  private Double totalVenta;
  private String estado;
  private List<DetalleVentaResponse> detalles;
}
