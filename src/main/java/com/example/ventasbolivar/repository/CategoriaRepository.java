package com.example.ventasbolivar.repository;

import com.example.ventasbolivar.model.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * CategoriaRepository.
 */
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
}
