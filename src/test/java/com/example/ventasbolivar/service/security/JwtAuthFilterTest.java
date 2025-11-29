package com.example.ventasbolivar.service.security;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.context.SecurityContextHolder;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Collections;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class JwtAuthFilterTest {

  @Mock
  private JwtService jwtService;

  @Mock
  private CustomUserDetailsService userService;

  @Mock
  private HttpServletRequest request;

  @Mock
  private HttpServletResponse response;

  @Mock
  private FilterChain chain;

  @InjectMocks
  private JwtAuthFilter filter;

  @BeforeEach
  void setUp() {
    SecurityContextHolder.clearContext();
  }

  @Test
  void doFilterInternal_tokenValido_autenticaUsuario() throws Exception {
    String token = "token123";
    String username = "user1";

    when(request.getHeader("Authorization")).thenReturn("Bearer " + token);
    when(jwtService.extractUsername(token)).thenReturn(username);

    UserDetails user = User.withUsername(username)
     .password("pass")
     .authorities(Collections.emptyList())
     .build();

    when(userService.loadUserByUsername(username)).thenReturn(user);
    when(jwtService.isValid(token, user)).thenReturn(true);

    filter.doFilterInternal(request, response, chain);

    assertNotNull(SecurityContextHolder.getContext().getAuthentication());
    assertEquals(username,
     SecurityContextHolder.getContext().getAuthentication().getName());

    verify(chain).doFilter(request, response);
  }

  @Test
  void doFilterInternal_tokenInvalido_noAutentica() throws Exception {
    String token = "token123";
    String username = "user1";

    when(request.getHeader("Authorization")).thenReturn("Bearer " + token);
    when(jwtService.extractUsername(token)).thenReturn(username);

    UserDetails user = User.withUsername(username)
     .password("pass")
     .authorities(Collections.emptyList())
     .build();

    when(userService.loadUserByUsername(username)).thenReturn(user);
    when(jwtService.isValid(token, user)).thenReturn(false);

    filter.doFilterInternal(request, response, chain);

    assertNull(SecurityContextHolder.getContext().getAuthentication());
    verify(chain).doFilter(request, response);
  }

  @Test
  void doFilterInternal_sinHeader_pasaFiltro() throws Exception {
    when(request.getHeader("Authorization")).thenReturn(null);

    filter.doFilterInternal(request, response, chain);

    assertNull(SecurityContextHolder.getContext().getAuthentication());
    verify(chain).doFilter(request, response);
  }

  @Test
  void doFilterInternal_headerNoBearer_pasaFiltro() throws Exception {
    when(request.getHeader("Authorization")).thenReturn("Basic abc123");

    filter.doFilterInternal(request, response, chain);

    assertNull(SecurityContextHolder.getContext().getAuthentication());
    verify(chain).doFilter(request, response);
  }
}