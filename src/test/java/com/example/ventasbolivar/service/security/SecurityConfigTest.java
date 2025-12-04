package com.example.ventasbolivar.service.security;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletMapping;
import jakarta.servlet.http.MappingMatch;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class SecurityConfigTest {

  @InjectMocks
  private SecurityConfig securityConfig;

  @Mock
  private CustomUserDetailsService userService;
  @Mock
  private JwtAuthFilter jwtFilter;

  @Mock
  private HttpServletRequest mockRequest;
  @Mock
  private HttpServletMapping mockMapping;
  @Mock
  private MappingMatch mockMappingMatch;

  @Test
  void corsConfigurationSource_shouldBeConfiguredCorrectlyToPassCoverage() {
    Mockito.when(mockRequest.getContextPath()).thenReturn("");
    Mockito.when(mockRequest.getRequestURI()).thenReturn("/api/test/path");

    Mockito.when(mockRequest.getHttpServletMapping()).thenReturn(mockMapping);
    Mockito.when(mockMapping.getMappingMatch()).thenReturn(mockMappingMatch);

    CorsConfigurationSource source = securityConfig.corsConfigurationSource();
    assertTrue(source instanceof UrlBasedCorsConfigurationSource);
    UrlBasedCorsConfigurationSource urlSource = (UrlBasedCorsConfigurationSource) source;
    CorsConfiguration configuration = urlSource.getCorsConfiguration(mockRequest);

    assertNotNull(configuration, "La configuración CORS no debe ser nula");
    assertEquals(List.of("http://localhost:5173"), configuration.getAllowedOrigins());
    assertEquals(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"), configuration.getAllowedMethods());
    assertEquals(List.of("*"), configuration.getAllowedHeaders());
    assertTrue(configuration.getAllowCredentials());

    assertEquals(3600L, configuration.getMaxAge());
  }
}