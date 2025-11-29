package com.example.ventasbolivar.model.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entidad Ingreso.
 */
@Entity
@Table(name = "ingreso")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ingreso {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer idingreso;

  @Column(name = "idproveedor")
  private Integer idproveedor;

  @Column(name = "idusuario")
  private Integer idusuario;

  @Column(name = "tipo_comprobante")
  private String tipoComprobante;

  @Column(name = "serie_comprobante")
  private String serieComprobante;

  @Column(name = "num_comprobante")
  private String numComprobante;

  @Column(name = "fecha_hora")
  private LocalDateTime fechahora;

  private BigDecimal impuesto;

  @Column(name = "total_compra")
  private BigDecimal totalcompra;

  private String estado;

  @OneToMany(mappedBy = "ingreso", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<DetalleIngreso> detalles;

}