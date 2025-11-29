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
public class PersonaResponse {
  private Integer id;
  private String nombre;
  private String tipoPersona;
  private String numDocumento;
  private String telefono;
}