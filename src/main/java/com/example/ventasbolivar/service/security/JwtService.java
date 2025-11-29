package com.example.ventasbolivar.service.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

/**
 * Component.
 */
@Component
public class JwtService {

  @Value("${app.jwt.secret}")
  private String jwtSecret;

  @Value("${app.jwt.expiration}")
  private long jwtExpiration;

  /**
   * Obtener llave.
   */
  private Key getKey() {
    return Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
  }

  /**
   * Generar token.
   */
  public String generateToken(UserDetails user, Map<String, Object> extra) {
    Date now = new Date();
    Date exp = new Date(now.getTime() + jwtExpiration);

    return Jwts.builder()
     .setClaims(extra)
     .setSubject(user.getUsername())
     .setIssuedAt(now)
     .setExpiration(exp)
     .signWith(getKey(), SignatureAlgorithm.HS256)
     .compact();
  }

  /**
   * Obtener claims.
   */
  public Claims getClaims(String token) {
    return Jwts.parserBuilder()
     .setSigningKey(getKey())
     .build()
     .parseClaimsJws(token)
     .getBody();
  }

  /**
   * Obtener nombre de usuario.
   */
  public String extractUsername(String token) {
    return getClaims(token).getSubject();
  }

  /**
   * Comprobar expiracion.
   */
  public boolean isExpired(String token) {
    return getClaims(token).getExpiration().before(new Date());
  }

  /**
   * Comprobar validez.
   */
  public boolean isValid(String token, UserDetails user) {
    return user.getUsername().equals(extractUsername(token))
        && !isExpired(token);
  }
}