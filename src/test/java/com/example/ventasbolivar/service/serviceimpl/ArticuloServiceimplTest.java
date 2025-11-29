package com.example.ventasbolivar.service.serviceimpl;

import com.example.ventasbolivar.model.dto.ArticuloRequest;
import com.example.ventasbolivar.model.dto.ArticuloResponse;
import com.example.ventasbolivar.model.entity.Articulo;
import com.example.ventasbolivar.model.entity.Categoria;
import com.example.ventasbolivar.repository.ArticuloRepository;
import com.example.ventasbolivar.repository.CategoriaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ArticuloServiceimplTest {

  @Mock
  private ArticuloRepository repo;

  @Mock
  private CategoriaRepository categoriaRepo;

  @InjectMocks
  private ArticuloServiceimpl service;

  private Articulo articulo;
  private Categoria categoria;
  private ArticuloRequest request;

  @BeforeEach
  void setup() {

    categoria = new Categoria();
    categoria.setId(2);
    categoria.setNombre("Tecnología");

    articulo = new Articulo();
    articulo.setId(1);
    articulo.setNombre("Laptop");
    articulo.setCodigo("L001");
    articulo.setStock(10);
    articulo.setPrecioVenta(3500.0);
    articulo.setCategoria(categoria);

    request = new ArticuloRequest();
    request.setIdCategoria(2);
    request.setNombre("Laptop");
    request.setCodigo("L001");
    request.setStock(10);
    request.setPrecioVenta(3500.0);
  }

  @Test
  void listar() {
    when(repo.findAll()).thenReturn(List.of(articulo));

    List<ArticuloResponse> resp = service.listar();

    assertEquals(1, resp.size());
    assertEquals("Laptop", resp.get(0).getNombre());
    assertEquals(2, resp.get(0).getIdCategoria());
    verify(repo).findAll();
  }

  @Test
  void obtener() {
    when(repo.findById(1)).thenReturn(Optional.of(articulo));

    ArticuloResponse resp = service.obtener(1);

    assertEquals("Laptop", resp.getNombre());
    assertEquals("Tecnología", resp.getNombreCategoria());
    verify(repo).findById(1);
  }

  @Test
  void obtener_NoExiste_DebeLanzarExcepcion() {
    when(repo.findById(1)).thenReturn(Optional.empty());

    assertThrows(NoSuchElementException.class, () -> service.obtener(1));
  }

  @Test
  void crear() {
    when(categoriaRepo.findById(2)).thenReturn(Optional.of(categoria));
    when(repo.save(any())).thenReturn(articulo);

    ArticuloResponse resp = service.crear(request);

    assertEquals("Laptop", resp.getNombre());
    assertEquals("Tecnología", resp.getNombreCategoria());
    verify(repo).save(any());
  }

  @Test
  void crear_CategoriaNoExiste_DebeLanzarExcepcion() {
    when(categoriaRepo.findById(2)).thenReturn(Optional.empty());

    assertThrows(NoSuchElementException.class, () -> service.crear(request));
    verify(repo, never()).save(any());
  }

  @Test
  void actualizar() {
    when(repo.findById(1)).thenReturn(Optional.of(articulo));
    when(categoriaRepo.findById(2)).thenReturn(Optional.of(categoria));
    when(repo.save(any())).thenReturn(articulo);

    ArticuloResponse resp = service.actualizar(1, request);

    assertEquals("Laptop", resp.getNombre());
    assertEquals(2, resp.getIdCategoria());
  }

  @Test
  void actualizar_ArticuloNoExiste_DebeLanzarExcepcion() {
    when(repo.findById(1)).thenReturn(Optional.empty());

    assertThrows(NoSuchElementException.class, () -> service.actualizar(1, request));
    verify(categoriaRepo, never()).findById(any());
    verify(repo, never()).save(any());
  }

  @Test
  void actualizar_CategoriaNoExiste_DebeLanzarExcepcion() {
    when(repo.findById(1)).thenReturn(Optional.of(articulo));
    when(categoriaRepo.findById(2)).thenReturn(Optional.empty());

    assertThrows(NoSuchElementException.class, () -> service.actualizar(1, request));
    verify(repo, never()).save(any());
  }


  @Test
  void eliminar() {
    when(repo.findById(1)).thenReturn(Optional.of(articulo));

    service.eliminar(1);

    verify(repo).delete(articulo);
  }

  @Test
  void eliminar_NoExiste_DebeLanzarExcepcion() {
    when(repo.findById(1)).thenReturn(Optional.empty());

    assertThrows(NoSuchElementException.class, () -> service.eliminar(1));
    verify(repo, never()).delete(any());
  }
}
