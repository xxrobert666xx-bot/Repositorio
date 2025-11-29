package com.example.ventasbolivar.model.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entidad venta.
 */
@Entity
@Table(name = "venta")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Venta {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "idventa")
  private Integer id;

  @ManyToOne
  @JoinColumn(name = "idcliente", nullable = false)
  private Persona cliente;

  @ManyToOne
  @JoinColumn(name = "idusuario", nullable = false)
  private Usuario usuario;

  @Column(name = "tipo_comprobante")
  private String tipoComprobante;

  @Column(name = "serie_comprobante")
  private String serieComprobante;

  @Column(name = "num_comprobante")
  private String numComprobante;

  @Column(name = "fecha_hora")
  private LocalDateTime fechaHora;

  private Double impuesto;

  @Column(name = "total_venta")
  private Double totalVenta;

  private String estado;

  @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<DetalleVenta> detalles;
}
