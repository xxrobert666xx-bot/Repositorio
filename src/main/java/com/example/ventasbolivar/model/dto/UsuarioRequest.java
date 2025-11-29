package com.example.ventasbolivar.model.dto;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * UsuarioRequest.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioRequest {
  private String nombre;
  private String tipoDocumento;
  private String numDocumento;
  private String direccion;
  private String telefono;
  private String email;
  private LocalDate fechaNacimiento;
  private String cargo;
  private String login;
  private String clave;
  private String imagen;
  private Integer condicion;
}
