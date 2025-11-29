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
 * Entidad DetalleIngreso.
 */
@Entity
@Table(name = "detalle_ingreso")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetalleIngreso {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer iddetalleIngreso;

  @ManyToOne
  @JoinColumn(name = "idingreso")
  private Ingreso ingreso;

  @Column(name = "idarticulo")
  private Integer idarticulo;

  @Column(name = "cantidad")
  private Integer cantidad;

  @Column(name = "precio_compra")
  private BigDecimal precioCompra;

  @Column(name = "precio_venta")
  private BigDecimal precioVenta;
}