package com.example.ventasbolivar.repository;

import com.example.ventasbolivar.model.entity.Permiso;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

/**
 * UsuarioPermisoRepository.
 */
public interface UsuarioPermisoRepository extends Repository<Permiso, Integer> {
  /**
   * Permisos de usuario.
   */
  @Query(value = """
       SELECT p.* 
       FROM permiso p
       JOIN usuario_permiso up ON p.idpermiso = up.idpermiso
       WHERE up.idusuario = :idUsuario
       """, nativeQuery = true)
  List<Permiso> findPermisosByUsuarioId(@Param("idUsuario") Integer idUsuario);

}
