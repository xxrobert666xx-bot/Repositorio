package com.example.ventasbolivar.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entidad Devolucion.
 */
@Entity
@Table(name = "devolucion")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Devolucion {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "iddevolucion")
  private Integer iddevolucion;

  @Column(name = "idventa", nullable = false)
  private Integer idventa;

  @Column(name = "idarticulo", nullable = false)
  private Integer idarticulo;

  @Column(nullable = false)
  private Integer cantidad;

  @Column(nullable = false, length = 256)
  private String motivo;

  @Column(name = "fecha_devolucion", nullable = false)
  private LocalDateTime fechaDevolucion;
}
