package com.example.ventasbolivar.controller;

import com.example.ventasbolivar.model.dto.LoginRequest;
import com.example.ventasbolivar.model.dto.LoginResponse;
import com.example.ventasbolivar.model.entity.Usuario;
import com.example.ventasbolivar.repository.UsuarioRepository;
import com.example.ventasbolivar.service.security.CustomUserDetailsService;
import com.example.ventasbolivar.service.security.JwtService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * AuthController.
 */
@RestController
@RequestMapping("/api/auth")
@CrossOrigin(
    origins = "http://localhost:5173",
    methods = {
        RequestMethod.GET,
        RequestMethod.POST,
        RequestMethod.PUT,
        RequestMethod.DELETE,
        RequestMethod.OPTIONS
    },
     allowedHeaders = "*"
)
@Tag(name = "Autenticación", description = "Gestión de autenticación del sistema")
public class AuthController {

  private final AuthenticationManager authManager;
  private final JwtService jwt;
  private final CustomUserDetailsService userService;
  private final UsuarioRepository usuarioRepo;

  /**
   * AuthController.
   */
  public AuthController(AuthenticationManager authManager,
                        JwtService jwt,
                        CustomUserDetailsService userService,
                        UsuarioRepository usuarioRepo) {
    this.authManager = authManager;
    this.jwt = jwt;
    this.userService = userService;
    this.usuarioRepo = usuarioRepo;
  }

  /**
   * Endpoint.
   */
  @Operation(
      summary = "Iniciar sesión",
      description = "Autentica al usuario con sus credenciales y "
       + "genera un token JWT para acceder al sistema."
  )
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "Autenticación exitosa, token generado"),
    @ApiResponse(responseCode = "401", description = "Credenciales inválidas"),
    @ApiResponse(responseCode = "500", description = "Error interno del servidor")
  })
  @PostMapping("/login")
  public ResponseEntity<?> login(
      @Parameter(description = "Credenciales de acceso del usuario")
      @RequestBody LoginRequest req) {
    try {
      authManager.authenticate(
        new UsernamePasswordAuthenticationToken(
          req.login(), req.clave()
       )
      );
    } catch (Exception e) {
      return ResponseEntity.status(401).body("Credenciales inválidas");
    }

    UserDetails user = userService.loadUserByUsername(req.login());
    Map<String, Object> claims = new HashMap<>();
    claims.put("permisos",
        user.getAuthorities().stream().map(a -> a.getAuthority()).toList()
    );

    String token = jwt.generateToken(user, claims);

    return ResponseEntity.ok(new LoginResponse(token, "Bearer"));
  }

  /**
   * Endpoint.
   */
  @Operation(
      summary = "Obtener datos del usuario autenticado",
      description = "Devuelve la información del usuario actualmente "
       + "autenticado según el token proporcionado."
  )
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "Usuario autenticado encontrado"),
    @ApiResponse(responseCode = "401", description = "No se proporcionó token o no es válido"),
    @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
  })
  @GetMapping("/me")
  public ResponseEntity<?> me(
      @Parameter(description = "Información de autenticación obtenida del token JWT")
      org.springframework.security.core.Authentication auth
  ) {
    if (auth == null) {
      return ResponseEntity.status(401).build();
    }

    String login = auth.getName();
    Optional<Usuario> u = usuarioRepo.findByLogin(login);

    return u.map(ResponseEntity::ok)
     .orElseGet(() -> ResponseEntity.notFound().build());
  }
}
