package com.example.ventasbolivar.service.security;

import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class JwtServiceTest {

  private JwtService jwtService;
  private UserDetails user;

  @BeforeEach
  void setUp() throws Exception {
    jwtService = new JwtService();

    // Inyectar jwtSecret y jwtExpiration usando reflection
    Field secretField = JwtService.class.getDeclaredField("jwtSecret");
    secretField.setAccessible(true);
    secretField.set(jwtService, "mi_secreto_muy_largo_para_testing_123456");

    Field expField = JwtService.class.getDeclaredField("jwtExpiration");
    expField.setAccessible(true);
    expField.set(jwtService, 1000L * 60 * 60); // 1 hora

    // Usuario de prueba
    user = User.withUsername("usuario1")
     .password("pass")
     .authorities(Collections.emptyList())
     .build();
  }

  @Test
  void generateToken_y_extractUsername_funciona() {
    Map<String, Object> extra = new HashMap<>();
    extra.put("rol", "ADMIN");

    String token = jwtService.generateToken(user, extra);
    assertNotNull(token);

    String username = jwtService.extractUsername(token);
    assertEquals("usuario1", username);
  }

  @Test
  void getClaims_devuelveClaimsCorrectamente() {
    String token = jwtService.generateToken(user, Collections.emptyMap());
    Claims claims = jwtService.getClaims(token);

    assertEquals("usuario1", claims.getSubject());
    assertNotNull(claims.getIssuedAt());
    assertNotNull(claims.getExpiration());
  }

  @Test
  void isExpired_devuelveFalse_paraTokenValido() {
    String token = jwtService.generateToken(user, Collections.emptyMap());
    assertFalse(jwtService.isExpired(token));
  }

  @Test
  void isValid_devuelveTrue_paraTokenValido() {
    String token = jwtService.generateToken(user, Collections.emptyMap());
    assertTrue(jwtService.isValid(token, user));
  }

  @Test
  void isValid_devuelveFalse_paraUsuarioIncorrecto() {
    String token = jwtService.generateToken(user, Collections.emptyMap());

    UserDetails otroUser = User.withUsername("otro")
     .password("pass")
     .authorities(Collections.emptyList())
     .build();

    assertFalse(jwtService.isValid(token, otroUser));
  }

  @Test
  void isExpired_devuelveTrue_paraTokenExpirado() throws Exception {
    // Configurar expiración muy corta
    Field expField = JwtService.class.getDeclaredField("jwtExpiration");
    expField.setAccessible(true);
    expField.set(jwtService, 1L); // 1 ms

    String token = jwtService.generateToken(user, Collections.emptyMap());
    Thread.sleep(5); // esperar que expire

    assertThrows(io.jsonwebtoken.ExpiredJwtException.class, () -> jwtService.getClaims(token));
  }
}