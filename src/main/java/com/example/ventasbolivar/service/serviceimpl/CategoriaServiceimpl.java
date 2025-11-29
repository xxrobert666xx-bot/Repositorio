package com.example.ventasbolivar.service.serviceimpl;

import com.example.ventasbolivar.model.dto.CategoriaRequest;
import com.example.ventasbolivar.model.dto.CategoriaResponse;
import com.example.ventasbolivar.model.entity.Categoria;
import com.example.ventasbolivar.repository.CategoriaRepository;
import com.example.ventasbolivar.service.CategoriaService;
import java.util.List;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * CategoriaServiceImpl.
 */
@Service
@RequiredArgsConstructor
public class CategoriaServiceimpl implements CategoriaService {

  private static final Logger log = LoggerFactory.getLogger(CategoriaServiceimpl.class);

  private final CategoriaRepository repo;

  @Override
  public List<CategoriaResponse> listar() {
    log.info("Listando todas las categorías");

    List<CategoriaResponse> lista = repo.findAll()
        .stream()
        .map(this::toResponse)
        .toList();

    log.debug("Total de categorías encontradas: {}", lista.size());
    return lista;
  }

  @Override
  public CategoriaResponse obtener(Integer id) {
    log.info("Obteniendo categoría con ID {}", id);

    Categoria c = repo.findById(id)
        .orElseThrow(() -> {
          log.warn("Categoría con ID {} no encontrada", id);
          return new NoSuchElementException("Categoría no encontrada");
        });

    log.debug("Categoría encontrada: {}", c.getNombre());
    return toResponse(c);
  }

  @Override
  public CategoriaResponse crear(CategoriaRequest req) {
    log.info("Creando categoría con nombre {}", req.getNombre());

    if (req.getNombre() == null || req.getNombre().isBlank()) {
      log.error("Error al crear categoría: nombre vacío");
      throw new IllegalArgumentException("Nombre inválido");
    }

    Categoria c = new Categoria();
    c.setNombre(req.getNombre());
    c.setDescripcion(req.getDescripcion());
    c.setCondicion(req.getCondicion());

    Categoria guardado = repo.save(c);

    log.info("Categoría creada exitosamente con ID {}", guardado.getId());
    return toResponse(guardado);
  }

  @Override
  public CategoriaResponse actualizar(Integer id, CategoriaRequest req) {
    log.info("Actualizando categoría con ID {}", id);

    Categoria c = repo.findById(id)
        .orElseThrow(() -> {
          log.warn("Categoría con ID {} no encontrada para actualización", id);
          return new NoSuchElementException("Categoría no encontrada");
        });

    c.setNombre(req.getNombre());
    c.setDescripcion(req.getDescripcion());
    c.setCondicion(req.getCondicion());

    repo.save(c);

    log.info("Categoría con ID {} actualizada correctamente", id);
    return toResponse(c);
  }

  @Override
  public void eliminar(Integer id) {
    log.info("Eliminando categoría con ID {}", id);

    Categoria c = repo.findById(id)
        .orElseThrow(() -> {
          log.warn("Categoría con ID {} no encontrada para eliminar", id);
          return new NoSuchElementException("Categoría no encontrada");
        });

    repo.delete(c);
    log.info("Categoría con ID {} eliminada correctamente", id);
  }

  private CategoriaResponse toResponse(Categoria c) {
    return new CategoriaResponse(
     c.getId(),
     c.getNombre()
    );
  }
}
