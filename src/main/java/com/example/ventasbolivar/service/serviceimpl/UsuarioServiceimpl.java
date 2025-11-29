package com.example.ventasbolivar.service.serviceimpl;

import com.example.ventasbolivar.model.dto.UsuarioRequest;
import com.example.ventasbolivar.model.dto.UsuarioResponse;
import com.example.ventasbolivar.model.entity.Usuario;
import com.example.ventasbolivar.repository.UsuarioRepository;
import com.example.ventasbolivar.service.UsuarioService;
import java.util.List;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * UsuarioServiceImpl.
 */
@Service
@RequiredArgsConstructor
public class UsuarioServiceimpl implements UsuarioService {

  private static final Logger log = LoggerFactory.getLogger(UsuarioServiceimpl.class);

  private final UsuarioRepository repo;

  @Override
  public List<UsuarioResponse> listar() {
    log.info("Listando todos los usuarios");
    return repo.findAll()
     .stream()
     .map(this::toResponse)
     .toList();
  }

  @Override
  public UsuarioResponse obtener(Integer id) {
    log.info("Obteniendo usuario con ID {}", id);
    Usuario u = repo.findById(id)
        .orElseThrow(() -> {
          log.error("Usuario con ID {} no encontrado", id);
          return new NoSuchElementException("Usuario no encontrado");
        });
    return toResponse(u);
  }

  @Override
  public UsuarioResponse crear(UsuarioRequest req) {
    log.info("Creando usuario: login={}, nombre={}", req.getLogin(), req.getNombre());
    Usuario u = new Usuario();
    copyData(req, u);

    Usuario saved = repo.save(u);
    log.info("Usuario creado con ID {}", saved.getId());

    return toResponse(saved);
  }

  @Override
  public UsuarioResponse actualizar(Integer id, UsuarioRequest req) {
    log.info("Actualizando usuario con ID {}", id);

    Usuario u = repo.findById(id)
        .orElseThrow(() -> {
          log.error("Usuario con ID {} no encontrado para actualización", id);
          return new NoSuchElementException("Usuario no encontrado");
        });

    copyData(req, u);

    Usuario saved = repo.save(u);
    log.info("Usuario actualizado correctamente con ID {}", saved.getId());

    return toResponse(saved);
  }

  @Override
  public void eliminar(Integer id) {
    log.warn("Eliminando usuario con ID {}", id);

    Usuario u = repo.findById(id)
        .orElseThrow(() -> {
          log.error("Usuario con ID {} no encontrado para eliminación", id);
          return new NoSuchElementException("Usuario no encontrado");
        });

    repo.delete(u);
    log.warn("Usuario con ID {} eliminado correctamente", id);
  }

  private void copyData(UsuarioRequest req, Usuario u) {
    u.setNombre(req.getNombre());
    u.setTipoDocumento(req.getTipoDocumento());
    u.setNumDocumento(req.getNumDocumento());
    u.setDireccion(req.getDireccion());
    u.setTelefono(req.getTelefono());
    u.setEmail(req.getEmail());
    u.setFechaNacimiento(req.getFechaNacimiento());
    u.setCargo(req.getCargo());
    u.setLogin(req.getLogin());
    u.setClave(req.getClave());
    u.setImagen(req.getImagen());
    u.setCondicion(req.getCondicion());
  }

  private UsuarioResponse toResponse(Usuario u) {
    return new UsuarioResponse(
     u.getId(),
     u.getNombre(),
     u.getLogin(),
     u.getCargo(),
     u.getCondicion()
    );
  }
}
