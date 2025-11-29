package com.example.ventasbolivar.service.serviceimpl;

import com.example.ventasbolivar.model.dto.CategoriaRequest;
import com.example.ventasbolivar.model.dto.CategoriaResponse;
import com.example.ventasbolivar.model.entity.Categoria;
import com.example.ventasbolivar.repository.CategoriaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoriaServiceimplTest {
  @Mock
  private CategoriaRepository repo;

  @InjectMocks
  private CategoriaServiceimpl service;

  @Test
  void listar() {
    Categoria c1 = new Categoria(1, "Cat1", "desc1", 1);
    Categoria c2 = new Categoria(2, "Cat2", "desc2", 0);

    when(repo.findAll()).thenReturn(List.of(c1, c2));

    List<CategoriaResponse> result = service.listar();

    Assertions.assertEquals(2, result.size());
    Assertions.assertEquals("Cat1", result.get(0).getNombre());
    Assertions.assertEquals("Cat2", result.get(1).getNombre());
  }

  @Test
  void obtener() {
    Categoria c = new Categoria(1, "Cat1", "desc1", 1);

    when(repo.findById(1)).thenReturn(Optional.of(c));

    CategoriaResponse result = service.obtener(1);

    Assertions.assertEquals(1, result.getId());
    Assertions.assertEquals("Cat1", result.getNombre());
  }

  @Test
  void obtenerNotFound() {
    when(repo.findById(1)).thenReturn(Optional.empty());

    Assertions.assertThrows(NoSuchElementException.class,
     () -> service.obtener(1));
  }

  @Test
  void crear() {
    CategoriaRequest req = new CategoriaRequest("Nueva", "desc", 1);
    Categoria saved = new Categoria(1, "Nueva", "desc", 1);

    when(repo.save(any(Categoria.class))).thenReturn(saved);

    CategoriaResponse res = service.crear(req);

    Assertions.assertEquals(1, res.getId());
    Assertions.assertEquals("Nueva", res.getNombre());
  }

  @Test
  void crearNombreInvalido() {
    CategoriaRequest req = new CategoriaRequest("   ", "desc", 1);

    Assertions.assertThrows(IllegalArgumentException.class,
     () -> service.crear(req));
  }

  @Test
  void actualizar() {
    Categoria existente = new Categoria(1, "Old", "old desc", 1);
    CategoriaRequest req = new CategoriaRequest("New", "new desc", 0);

    when(repo.findById(1)).thenReturn(Optional.of(existente));
    when(repo.save(existente)).thenReturn(existente);

    CategoriaResponse res = service.actualizar(1, req);

    Assertions.assertEquals(1, res.getId());
    Assertions.assertEquals("New", res.getNombre());
  }

  @Test
  void actualizarNotFound() {
    CategoriaRequest req = new CategoriaRequest("New", "desc", 1);

    when(repo.findById(1)).thenReturn(Optional.empty());

    Assertions.assertThrows(NoSuchElementException.class,
     () -> service.actualizar(1, req));
  }

  @Test
  void eliminar() {
    Categoria c = new Categoria(1, "Cat", "desc", 1);

    when(repo.findById(1)).thenReturn(Optional.of(c));

    service.eliminar(1);

    verify(repo).delete(c);
  }

  @Test
  void eliminarNotFound() {
    when(repo.findById(1)).thenReturn(Optional.empty());

    Assertions.assertThrows(NoSuchElementException.class,
     () -> service.eliminar(1));
  }

}