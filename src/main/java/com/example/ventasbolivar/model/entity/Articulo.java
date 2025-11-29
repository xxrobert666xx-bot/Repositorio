package com.example.ventasbolivar.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entidad articulo.
 */
@Entity
@Table(name = "articulo")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Articulo {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "idarticulo")
  private Integer id;

  @ManyToOne
  @JoinColumn(name = "idcategoria")
  private Categoria categoria;

  @Column(name = "codigo")
  private String codigo;

  @Column(name = "nombre", nullable = false)
  private String nombre;

  @Column(name = "stock")
  private Integer stock;

  @Column(name = "precio_venta", nullable = false)
  private Double precioVenta;

  @Column(name = "descripcion")
  private String descripcion;

  @Column(name = "imagen")
  private String imagen;

  @Column(name = "condicion")
  private Integer condicion;

  @Column(name = "precio_compra")
  private Double precioCompra;
}
