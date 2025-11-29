package com.example.ventasbolivar.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Entidad Usuario.
 */
@Entity
@Table(name = "usuario")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "idusuario")
  private Integer id;

  private String nombre;

  @Column(name = "tipo_documento")
  private String tipoDocumento;

  @Column(name = "num_documento")
  private String numDocumento;

  private String direccion;
  private String telefono;
  private String email;

  @Column(name = "fecha_nacimiento")
  private LocalDate fechaNacimiento;

  private String cargo;
  private String login;
  private String clave;
  private String imagen;
  private Integer condicion;
}
