package com.example.ventasbolivar.service.serviceimpl;

import com.example.ventasbolivar.model.dto.ArticuloRequest;
import com.example.ventasbolivar.model.dto.ArticuloResponse;
import com.example.ventasbolivar.model.entity.Articulo;
import com.example.ventasbolivar.model.entity.Categoria;
import com.example.ventasbolivar.repository.ArticuloRepository;
import com.example.ventasbolivar.repository.CategoriaRepository;
import com.example.ventasbolivar.service.ArticuloService;
import java.util.List;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * ArticuloServiceImpl.
 */
@Service
@RequiredArgsConstructor
public class ArticuloServiceimpl implements ArticuloService {

  private static final Logger log = LoggerFactory.getLogger(ArticuloServiceimpl.class);

  private final ArticuloRepository repo;
  private final CategoriaRepository categoriaRepo;

  @Override
  public List<ArticuloResponse> listar() {
    log.info("Listando todos los artículos");
    List<ArticuloResponse> lista = repo.findAll().stream()
        .map(this::toResponse)
        .toList();

    log.debug("Total de artículos encontrados: {}", lista.size());
    return lista;
  }

  @Override
  public ArticuloResponse obtener(Integer id) {
    log.info("Obteniendo artículo con ID {}", id);
    Articulo a = repo.findById(id)
        .orElseThrow(() -> {
          log.warn("Artículo con ID {} no encontrado", id);
          return new NoSuchElementException("Artículo no encontrado");
        });

    log.debug("Artículo encontrado: {}", a.getNombre());
    return toResponse(a);
  }

  @Override
  public ArticuloResponse crear(ArticuloRequest req) {
    log.info("Creando artículo con código {}", req.getCodigo());

    Categoria categoria = categoriaRepo.findById(req.getIdCategoria())
        .orElseThrow(() -> {
          log.warn("Categoría con ID {} no encontrada", req.getIdCategoria());
          return new NoSuchElementException("Categoría no encontrada");
        });

    Articulo a = new Articulo();
    a.setCategoria(categoria);
    a.setCodigo(req.getCodigo());
    a.setNombre(req.getNombre());
    a.setStock(req.getStock());
    a.setPrecioVenta(req.getPrecioVenta());
    a.setDescripcion(req.getDescripcion());
    a.setImagen(req.getImagen());
    a.setCondicion(req.getCondicion());
    a.setPrecioCompra(req.getPrecioCompra());

    Articulo guardado = repo.save(a);

    log.info("Artículo creado exitosamente con ID {}", guardado.getId());
    return toResponse(guardado);
  }

  @Override
  public ArticuloResponse actualizar(Integer id, ArticuloRequest req) {
    log.info("Actualizando artículo con ID {}", id);

    Articulo a = repo.findById(id)
        .orElseThrow(() -> {
          log.warn("Artículo con ID {} no encontrado para actualización", id);
          return new NoSuchElementException("Artículo no encontrado");
        });

    Categoria categoria = categoriaRepo.findById(req.getIdCategoria())
        .orElseThrow(() -> {
          log.warn("Categoría con ID {} no encontrada para actualizar artículo {}",
              req.getIdCategoria(), id);
          return new NoSuchElementException("Categoría no encontrada");
        });

    a.setCategoria(categoria);
    a.setCodigo(req.getCodigo());
    a.setNombre(req.getNombre());
    a.setStock(req.getStock());
    a.setPrecioVenta(req.getPrecioVenta());
    a.setDescripcion(req.getDescripcion());
    a.setImagen(req.getImagen());
    a.setCondicion(req.getCondicion());
    a.setPrecioCompra(req.getPrecioCompra());

    repo.save(a);

    log.info("Artículo con ID {} actualizado correctamente", id);
    return toResponse(a);
  }

  @Override
  public void eliminar(Integer id) {
    log.info("Eliminando artículo con ID {}", id);

    Articulo a = repo.findById(id)
        .orElseThrow(() -> {
          log.warn("Artículo con ID {} no encontrado para eliminar", id);
          return new NoSuchElementException("Artículo no encontrado");
        });

    repo.delete(a);
    log.info("Artículo con ID {} eliminado correctamente", id);
  }

  private ArticuloResponse toResponse(Articulo a) {
    ArticuloResponse r = new ArticuloResponse();
    r.setId(a.getId());
    r.setNombre(a.getNombre());
    r.setCodigo(a.getCodigo());
    r.setStock(a.getStock());
    r.setPrecioVenta(a.getPrecioVenta());

    r.setIdCategoria(a.getCategoria().getId());
    r.setNombreCategoria(a.getCategoria().getNombre());

    return r;
  }
}
