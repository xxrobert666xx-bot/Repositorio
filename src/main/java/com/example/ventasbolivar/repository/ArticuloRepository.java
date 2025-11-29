package com.example.ventasbolivar.repository;

import com.example.ventasbolivar.model.entity.Articulo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * ArticuloRepository.
 */
public interface ArticuloRepository extends JpaRepository<Articulo, Integer> {
}
