package com.example.ventasbolivar.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entidad DetalleVenta.
 */
@Entity
@Table(name = "detalle_venta")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetalleVenta {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "iddetalle_venta")
  private Integer id;

  @ManyToOne
  @JoinColumn(name = "idventa", nullable = false)
  private Venta venta;

  @ManyToOne
  @JoinColumn(name = "idarticulo", nullable = false)
  private Articulo articulo;

  private Integer cantidad;

  @Column(name = "precio_venta")
  private BigDecimal precioVenta;

  private BigDecimal descuento;
}
