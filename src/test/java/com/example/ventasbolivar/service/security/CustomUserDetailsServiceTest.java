package com.example.ventasbolivar.service.security;

import com.example.ventasbolivar.model.entity.Permiso;
import com.example.ventasbolivar.model.entity.Usuario;
import com.example.ventasbolivar.repository.UsuarioPermisoRepository;
import com.example.ventasbolivar.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomUserDetailsServiceTest {

  @Mock
  private UsuarioRepository usuarioRepository;

  @Mock
  private UsuarioPermisoRepository usuarioPermisoRepository;

  @InjectMocks
  private CustomUserDetailsService service;

  private Usuario usuarioActivo;
  private Usuario usuarioInactivo;
  private List<Permiso> permisos;

  @BeforeEach
  void setUp() {
    usuarioActivo = new Usuario();
    usuarioActivo.setId(1);
    usuarioActivo.setLogin("user1");
    usuarioActivo.setClave("pass1");
    usuarioActivo.setCondicion(1);

    usuarioInactivo = new Usuario();
    usuarioInactivo.setId(2);
    usuarioInactivo.setLogin("user2");
    usuarioInactivo.setClave("pass2");
    usuarioInactivo.setCondicion(0);

    Permiso p1 = new Permiso();
    p1.setNombre("ROLE_ADMIN");

    Permiso p2 = new Permiso();
    p2.setNombre("ROLE_USER");

    permisos = List.of(p1, p2);
  }

  @Test
  void loadUserByUsername_usuarioEncontrado_activo() {
    when(usuarioRepository.findByLogin("user1")).thenReturn(Optional.of(usuarioActivo));
    when(usuarioPermisoRepository.findPermisosByUsuarioId(1)).thenReturn(permisos);

    UserDetails userDetails = service.loadUserByUsername("user1");

    assertEquals("user1", userDetails.getUsername());
    assertEquals("pass1", userDetails.getPassword());
    assertTrue(userDetails.isEnabled());
    assertTrue(userDetails.getAuthorities().stream()
     .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN")));
    assertTrue(userDetails.getAuthorities().stream()
     .anyMatch(a -> a.getAuthority().equals("ROLE_USER")));
  }

  @Test
  void loadUserByUsername_usuarioNoEncontrado_lanzaExcepcion() {
    when(usuarioRepository.findByLogin("notfound")).thenReturn(Optional.empty());

    assertThrows(UsernameNotFoundException.class,
     () -> service.loadUserByUsername("notfound"));
  }

  @Test
  void loadUserByUsername_usuarioEncontrado_inactivo() {
    when(usuarioRepository.findByLogin("user2")).thenReturn(Optional.of(usuarioInactivo));
    when(usuarioPermisoRepository.findPermisosByUsuarioId(2)).thenReturn(permisos);

    UserDetails userDetails = service.loadUserByUsername("user2");

    assertFalse(userDetails.isEnabled());
  }
}