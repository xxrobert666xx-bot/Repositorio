package com.example.ventasbolivar.controller;

import com.example.ventasbolivar.model.dto.LoginRequest;
import com.example.ventasbolivar.model.dto.LoginResponse;
import com.example.ventasbolivar.model.entity.Usuario;
import com.example.ventasbolivar.repository.UsuarioRepository;
import com.example.ventasbolivar.service.security.CustomUserDetailsService;
import com.example.ventasbolivar.service.security.JwtService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthControllerTest {

  private AuthController controller;
  private AuthenticationManager authManager;
  private JwtService jwtService;
  private CustomUserDetailsService userService;
  private UsuarioRepository usuarioRepo;

  @BeforeEach
  void setUp() {
    authManager = mock(AuthenticationManager.class);
    jwtService = mock(JwtService.class);
    userService = mock(CustomUserDetailsService.class);
    usuarioRepo = mock(UsuarioRepository.class);

    controller = new AuthController(authManager, jwtService, userService, usuarioRepo);
  }

  @Test
  void login_exitoso_devuelveToken() {
    LoginRequest req = new LoginRequest("user1", "clave123");

    UserDetails userDetails = User.builder()
     .username("user1")
     .password("clave123")
     .authorities(List.of())
     .build();

    when(authManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
     .thenReturn(mock(Authentication.class));

    when(userService.loadUserByUsername("user1")).thenReturn(userDetails);
    when(jwtService.generateToken(eq(userDetails), anyMap())).thenReturn("jwt-token");

    ResponseEntity<?> resp = controller.login(req);
    assertEquals(200, resp.getStatusCodeValue());
    assertTrue(resp.getBody() instanceof LoginResponse);

    LoginResponse body = (LoginResponse) resp.getBody();
    assertEquals("jwt-token", body.token());
    assertEquals("Bearer", body.type());
  }

  @Test
  void login_credencialesInvalidas_devuelve401() {
    LoginRequest req = new LoginRequest("user1", "wrong");

    doThrow(BadCredentialsException.class)
     .when(authManager).authenticate(any(UsernamePasswordAuthenticationToken.class));

    ResponseEntity<?> resp = controller.login(req);
    assertEquals(401, resp.getStatusCodeValue());
    assertEquals("Credenciales inválidas", resp.getBody());
  }

  @Test
  void me_usuarioExistente_devuelveUsuario() {
    Usuario usuario = new Usuario();
    usuario.setLogin("user1");

    Authentication auth = mock(Authentication.class);
    when(auth.getName()).thenReturn("user1");
    when(usuarioRepo.findByLogin("user1")).thenReturn(Optional.of(usuario));

    ResponseEntity<?> resp = controller.me(auth);
    assertEquals(200, resp.getStatusCodeValue());
    assertEquals(usuario, resp.getBody());
  }

  @Test
  void me_usuarioNoEncontrado_devuelve404() {
    Authentication auth = mock(Authentication.class);
    when(auth.getName()).thenReturn("user1");
    when(usuarioRepo.findByLogin("user1")).thenReturn(Optional.empty());

    ResponseEntity<?> resp = controller.me(auth);
    assertEquals(404, resp.getStatusCodeValue());
  }

  @Test
  void me_authNulo_devuelve401() {
    ResponseEntity<?> resp = controller.me(null);
    assertEquals(401, resp.getStatusCodeValue());
  }
}