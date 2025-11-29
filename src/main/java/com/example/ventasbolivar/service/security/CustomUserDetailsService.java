package com.example.ventasbolivar.service.security;

import com.example.ventasbolivar.model.entity.Permiso;
import com.example.ventasbolivar.model.entity.Usuario;
import com.example.ventasbolivar.repository.UsuarioPermisoRepository;
import com.example.ventasbolivar.repository.UsuarioRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * CustomUserDetailsService.
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

  private final UsuarioRepository usuarioRepository;
  private final UsuarioPermisoRepository usuarioPermisoRepository;

  /**
   * CustomUserDetailsService.
   */
  public CustomUserDetailsService(
      UsuarioRepository usuarioRepository,
      UsuarioPermisoRepository usuarioPermisoRepository
  ) {
    this.usuarioRepository = usuarioRepository;
    this.usuarioPermisoRepository = usuarioPermisoRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    Usuario usuario = usuarioRepository.findByLogin(username)
        .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

    List<Permiso> permisos =
        usuarioPermisoRepository.findPermisosByUsuarioId(usuario.getId());

    List<SimpleGrantedAuthority> authorities = permisos.stream()
        .map(p -> new SimpleGrantedAuthority(p.getNombre()))
        .collect(Collectors.toList());

    boolean enabled = usuario.getCondicion() != null && usuario.getCondicion() == 1;

    return org.springframework.security.core.userdetails.User
     .withUsername(usuario.getLogin())
     .password(usuario.getClave())
     .authorities(authorities)
     .disabled(!enabled)
     .build();
  }
}