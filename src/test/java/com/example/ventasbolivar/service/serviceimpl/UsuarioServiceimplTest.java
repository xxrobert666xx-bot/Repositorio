package com.example.ventasbolivar.service.serviceimpl;

import com.example.ventasbolivar.model.dto.UsuarioRequest;
import com.example.ventasbolivar.model.dto.UsuarioResponse;
import com.example.ventasbolivar.model.entity.Usuario;
import com.example.ventasbolivar.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceimplTest {

  @Mock
  private UsuarioRepository repo;

  @InjectMocks
  private UsuarioServiceimpl service;

  private Usuario usuario;
  private UsuarioRequest request;

  @BeforeEach
  void setup() {
    usuario = new Usuario();
    usuario.setId(1);
    usuario.setNombre("Juan Perez");
    usuario.setLogin("juan");
    usuario.setCargo("Admin");
    usuario.setClave("1234");
    usuario.setImagen("img.png");
    usuario.setCondicion(1);

    request = new UsuarioRequest(
     "Juan Perez",
     "DNI",
     "12345678",
     "Av Peru",
     "987654321",
     "juan@test.com",
     LocalDate.of(1990, 1, 1),
     "Admin",
     "juan",
     "1234",
     "img.png",
     1
    );
  }

  @Test
  void listar() {
    when(repo.findAll()).thenReturn(List.of(usuario));

    List<UsuarioResponse> resp = service.listar();

    assertEquals(1, resp.size());
    assertEquals("juan", resp.get(0).getLogin());
  }

  @Test
  void obtener() {
    when(repo.findById(1)).thenReturn(Optional.of(usuario));

    UsuarioResponse resp = service.obtener(1);

    assertEquals("juan", resp.getLogin());
  }

  @Test
  void obtener_NotFound() {
    when(repo.findById(1)).thenReturn(Optional.empty());

    assertThrows(NoSuchElementException.class, () -> service.obtener(1));

    verify(repo).findById(1);
  }

  @Test
  void crear() {
    when(repo.save(any())).thenReturn(usuario);

    UsuarioResponse resp = service.crear(request);

    assertEquals("juan", resp.getLogin());

    ArgumentCaptor<Usuario> captor = ArgumentCaptor.forClass(Usuario.class);
    verify(repo).save(captor.capture());

    Usuario saved = captor.getValue();
    assertEquals("Juan Perez", saved.getNombre());
    assertEquals("DNI", saved.getTipoDocumento());
    assertEquals("12345678", saved.getNumDocumento());
    assertEquals("Av Peru", saved.getDireccion());
  }

  @Test
  void actualizar() {
    when(repo.findById(1)).thenReturn(Optional.of(usuario));
    when(repo.save(any())).thenReturn(usuario);

    UsuarioResponse resp = service.actualizar(1, request);

    assertEquals("juan", resp.getLogin());
    verify(repo).save(any());
  }

  @Test
  void actualizar_NotFound() {
    when(repo.findById(1)).thenReturn(Optional.empty());

    assertThrows(NoSuchElementException.class, () -> service.actualizar(1, request));

    verify(repo).findById(1);
  }

  @Test
  void eliminar() {
    when(repo.findById(1)).thenReturn(Optional.of(usuario));

    service.eliminar(1);

    verify(repo).delete(usuario);
  }

  @Test
  void eliminar_NotFound() {
    when(repo.findById(1)).thenReturn(Optional.empty());

    assertThrows(NoSuchElementException.class, () -> service.eliminar(1));

    verify(repo).findById(1);
  }
}
