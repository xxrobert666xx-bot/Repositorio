package com.example.ventasbolivar.service.serviceimpl;

import com.example.ventasbolivar.model.dto.PersonaRequest;
import com.example.ventasbolivar.model.dto.PersonaResponse;
import com.example.ventasbolivar.model.entity.Persona;
import com.example.ventasbolivar.repository.PersonaRepository;
import com.example.ventasbolivar.service.PersonaService;
import java.util.List;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * PersonaServiceImpl.
 */
@Service
@RequiredArgsConstructor
public class PersonaServiceimpl implements PersonaService {

  private static final Logger log = LoggerFactory.getLogger(PersonaServiceimpl.class);

  private final PersonaRepository repo;

  @Override
  public List<PersonaResponse> listar() {
    log.info("Listando todas las personas");

    List<PersonaResponse> lista = repo.findAll()
        .stream()
        .map(this::toResponse)
        .toList();

    log.info("Total personas encontradas: {}", lista.size());
    return lista;
  }

  @Override
  public PersonaResponse obtener(Integer id) {
    log.info("Obteniendo persona con ID {}", id);

    Persona p = repo.findById(id)
        .orElseThrow(() -> {
          log.warn("Persona con ID {} no encontrada", id);
          return new NoSuchElementException("Persona no encontrada");
        });

    log.debug("Persona encontrada: {}", p.getNombre());
    return toResponse(p);
  }

  @Override
  public PersonaResponse crear(PersonaRequest req) {
    log.info("Registrando nueva persona: {}", req.getNombre());

    if (req.getNombre() == null || req.getNombre().isBlank()) {
      log.error("Error al crear persona: nombre inválido");
      throw new IllegalArgumentException("Nombre inválido");
    }

    Persona p = new Persona();
    p.setTipoPersona(req.getTipoPersona());
    p.setNombre(req.getNombre());
    p.setTipoDocumento(req.getTipoDocumento());
    p.setNumDocumento(req.getNumDocumento());
    p.setDireccion(req.getDireccion());
    p.setTelefono(req.getTelefono());
    p.setEmail(req.getEmail());

    Persona guardado = repo.save(p);

    log.info("Persona creada exitosamente con ID {}", guardado.getId());
    return toResponse(guardado);
  }

  @Override
  public PersonaResponse actualizar(Integer id, PersonaRequest req) {
    log.info("Actualizando persona con ID {}", id);

    Persona p = repo.findById(id)
        .orElseThrow(() -> {
          log.warn("Persona con ID {} no encontrada para actualización", id);
          return new NoSuchElementException("Persona no encontrada");
        });

    p.setTipoPersona(req.getTipoPersona());
    p.setNombre(req.getNombre());
    p.setTipoDocumento(req.getTipoDocumento());
    p.setNumDocumento(req.getNumDocumento());
    p.setDireccion(req.getDireccion());
    p.setTelefono(req.getTelefono());
    p.setEmail(req.getEmail());

    repo.save(p);

    log.info("Persona con ID {} actualizada correctamente", id);
    return toResponse(p);
  }

  @Override
  public void eliminar(Integer id) {
    log.info("Eliminando persona con ID {}", id);

    Persona p = repo.findById(id)
        .orElseThrow(() -> {
          log.warn("Persona con ID {} no encontrada para eliminación", id);
          return new NoSuchElementException("Persona no encontrada");
        });

    repo.delete(p);

    log.info("Persona con ID {} eliminada correctamente", id);
  }

  @Override
  public List<PersonaResponse> listarClientes() {
    log.info("Listando clientes");

    List<PersonaResponse> lista = repo.findAll()
        .stream()
        .filter(p -> "Cliente".equalsIgnoreCase(p.getTipoPersona()))
        .map(this::toResponse)
        .toList();

    log.info("Total clientes encontrados: {}", lista.size());
    return lista;
  }

  @Override
  public List<PersonaResponse> listarProveedores() {
    log.info("Listando proveedores");

    List<PersonaResponse> lista = repo.findAll()
        .stream()
        .filter(p -> "Proveedor".equalsIgnoreCase(p.getTipoPersona()))
        .map(this::toResponse)
        .toList();

    log.info("Total proveedores encontrados: {}", lista.size());
    return lista;
  }

  private PersonaResponse toResponse(Persona p) {
    return new PersonaResponse(
     p.getId(),
     p.getNombre(),
     p.getTipoPersona(),
     p.getNumDocumento(),
     p.getTelefono()
    );
  }

}