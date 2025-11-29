package com.example.ventasbolivar.repository;

import com.example.ventasbolivar.model.entity.Usuario;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * UsuarioRepository.
 */
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
  /**
   * Metodo para encontrar por login.
   */
  Optional<Usuario> findByLogin(String login);
}