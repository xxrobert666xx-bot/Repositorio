package com.example.ventasbolivar.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * PersonaResponse.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonaRequest {
  private String tipoPersona;
  private String nombre;
  private String tipoDocumento;
  private String numDocumento;
  private String direccion;
  private String telefono;
  private String email;
}