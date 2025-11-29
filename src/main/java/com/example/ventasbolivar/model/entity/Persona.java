package com.example.ventasbolivar.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entidad persona.
 */
@Entity
@Table(name = "persona")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Persona {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "idpersona")
  private Integer id;

  @Column(name = "tipo_persona", nullable = false)
  private String tipoPersona;

  @Column(nullable = false)
  private String nombre;

  @Column(name = "tipo_documento")
  private String tipoDocumento;

  @Column(name = "num_documento")
  private String numDocumento;

  private String direccion;

  private String telefono;

  private String email;
}