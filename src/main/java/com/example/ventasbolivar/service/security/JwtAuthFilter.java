package com.example.ventasbolivar.service.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * JwtAuthFilter.
 */
@Component
public class JwtAuthFilter extends OncePerRequestFilter {

  private final JwtService jwtService;
  private final CustomUserDetailsService userService;

  /**
   * JwtAuthFilter.
   */
  public JwtAuthFilter(JwtService jwtService, CustomUserDetailsService userService) {
    this.jwtService = jwtService;
    this.userService = userService;
  }

  @Override
  protected void doFilterInternal(
      HttpServletRequest req,
      HttpServletResponse res,
      FilterChain chain
  ) throws ServletException, IOException {

    final String auth = req.getHeader("Authorization");
    String token = null;
    String username = null;

    if (auth != null && auth.startsWith("Bearer ")) {
      token = auth.substring(7);
      try {
        username = jwtService.extractUsername(token);
      } catch (Exception ignored) {
        // Se ignora porque el token puede estar malformado o expirado.
      }
    }

    if (username != null
        && SecurityContextHolder.getContext().getAuthentication() == null) {

      UserDetails user = userService.loadUserByUsername(username);

      if (jwtService.isValid(token, user)) {

        UsernamePasswordAuthenticationToken authToken =
            new UsernamePasswordAuthenticationToken(
                user, null, user.getAuthorities());

        authToken.setDetails(
          new WebAuthenticationDetailsSource().buildDetails(req)
        );

        SecurityContextHolder.getContext().setAuthentication(authToken);
      }
    }

    chain.doFilter(req, res);
  }
}