package com.example.ventasbolivar.service.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import java.util.Arrays;
import java.util.List;

/**
 Configuration.
 */
@Configuration
@EnableMethodSecurity
public class SecurityConfig {

  private final CustomUserDetailsService userService;
  private final JwtAuthFilter jwtFilter;

  /**
   * SecurityConfig.
   */
  public SecurityConfig(CustomUserDetailsService userService,
                        JwtAuthFilter jwtFilter) {
    this.userService = userService;
    this.jwtFilter = jwtFilter;
  }

  /**
   * Bean.
   */
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.cors(c -> c.configurationSource(corsConfigurationSource()));
    // Security justification: This API is completely stateless and uses JWT authentication.
    // No sessions or cookies are used, so CSRF protection does
    // not apply and it is safe to disable it.
    http.csrf(csrf -> csrf.disable());

    http.authorizeHttpRequests(auth -> auth
        .requestMatchers(
         "/v3/api-docs/**",
         "/swagger-ui/**",
         "/swagger-ui.html"
     ).permitAll()
        .requestMatchers("/api/auth/**").permitAll()
        .anyRequest().authenticated()
    );

    http.sessionManagement(sm ->
        sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    );

    http.authenticationProvider(authenticationProvider());
    http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

    return http.build();
  }

  /**
   * Bean.
   */
  @Bean
  public CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();
    configuration.setAllowedOrigins(List.of("http://localhost:5173")); 
    configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS")); 
    configuration.setAllowedHeaders(List.of("*")); 
    configuration.setAllowCredentials(true); 
    configuration.setMaxAge(3600L); 
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration); 
    
    return source;
  }
 
  /**
   * Bean.
   */
  @Bean
  public AuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    provider.setUserDetailsService(userService);
    provider.setPasswordEncoder(passwordEncoder());
    return provider;
  }

  /**
   * Bean.
   */
  @Bean
  public AuthenticationManager authManager(AuthenticationConfiguration c) throws Exception {
    return c.getAuthenticationManager();
  }

  /**
   * Bean.
   */
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder(); // RECOMENDADO
  }
}
