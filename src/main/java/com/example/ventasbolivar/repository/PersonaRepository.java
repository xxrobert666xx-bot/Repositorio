package com.example.ventasbolivar.repository;

import com.example.ventasbolivar.model.entity.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * PersonaRepository.
 */
public interface PersonaRepository extends JpaRepository<Persona, Integer> {
}
