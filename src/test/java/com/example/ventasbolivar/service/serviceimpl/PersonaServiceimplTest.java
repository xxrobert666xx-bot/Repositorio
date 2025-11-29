package com.example.ventasbolivar.service.serviceimpl;

import com.example.ventasbolivar.model.dto.PersonaRequest;
import com.example.ventasbolivar.model.dto.PersonaResponse;
import com.example.ventasbolivar.model.entity.Persona;
import com.example.ventasbolivar.repository.PersonaRepository;
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
class PersonaServiceimplTest {

  @Mock
  private PersonaRepository repo;

  @InjectMocks
  private PersonaServiceimpl service;

  private Persona persona;
  private PersonaRequest request;

  @BeforeEach
  void setup() {
    persona = new Persona();
    persona.setId(1);
    persona.setTipoPersona("Cliente");
    persona.setNombre("Juan Perez");
    persona.setTipoDocumento("DNI");
    persona.setNumDocumento("12345678");
    persona.setTelefono("987654321");
    persona.setEmail("juan@test.com");

    request = new PersonaRequest();
    request.setTipoPersona("Cliente");
    request.setNombre("Juan Perez");
    request.setTipoDocumento("DNI");
    request.setNumDocumento("12345678");
    request.setTelefono("987654321");
    request.setEmail("juan@test.com");
  }

  @Test
  void listar() {
    when(repo.findAll()).thenReturn(List.of(persona));

    List<PersonaResponse> resp = service.listar();

    assertEquals(1, resp.size());
    assertEquals("Juan Perez", resp.get(0).getNombre());
    verify(repo).findAll();
  }

  @Test
  void obtener() {
    when(repo.findById(1)).thenReturn(Optional.of(persona));

    PersonaResponse resp = service.obtener(1);

    assertEquals("Juan Perez", resp.getNombre());
    verify(repo).findById(1);
  }

  @Test
  void obtener_NoExiste_DebeLanzarExcepcion() {
    when(repo.findById(1)).thenReturn(Optional.empty());

    assertThrows(NoSuchElementException.class, () -> service.obtener(1));
    verify(repo).findById(1);
  }

  @Test
  void crear() {
    when(repo.save(any())).thenReturn(persona);

    PersonaResponse resp = service.crear(request);

    assertEquals("Juan Perez", resp.getNombre());
    assertEquals("Cliente", resp.getTipoPersona());
    verify(repo).save(any());
  }

  @Test
  void crear_NombreInvalido_DebeLanzarExcepcion() {
    request.setNombre("");

    assertThrows(IllegalArgumentException.class, () -> service.crear(request));
    verify(repo, never()).save(any());
  }


  @Test
  void actualizar() {
    when(repo.findById(1)).thenReturn(Optional.of(persona));
    when(repo.save(any())).thenReturn(persona);

    PersonaResponse resp = service.actualizar(1, request);

    assertEquals("Juan Perez", resp.getNombre());
  }

  @Test
  void actualizar_NoExiste_DebeLanzarExcepcion() {
    when(repo.findById(1)).thenReturn(Optional.empty());

    assertThrows(NoSuchElementException.class, () -> service.actualizar(1, request));
    verify(repo, never()).save(any());
  }

  @Test
  void eliminar() {
    when(repo.findById(1)).thenReturn(Optional.of(persona));

    service.eliminar(1);

    verify(repo).delete(persona);
  }

  @Test
  void eliminar_NoExiste_DebeLanzarExcepcion() {
    when(repo.findById(1)).thenReturn(Optional.empty());

    assertThrows(NoSuchElementException.class, () -> service.eliminar(1));
    verify(repo, never()).delete(any());
  }

  @Test
  void listarClientes() {
    persona.setTipoPersona("Cliente");
    when(repo.findAll()).thenReturn(List.of(persona));

    List<PersonaResponse> resp = service.listarClientes();

    assertEquals(1, resp.size());
    assertEquals("Cliente", resp.get(0).getTipoPersona());
  }

  @Test
  void listarProveedores() {
    persona.setTipoPersona("Proveedor");
    when(repo.findAll()).thenReturn(List.of(persona));

    List<PersonaResponse> resp = service.listarProveedores();

    assertEquals(1, resp.size());
    assertEquals("Proveedor", resp.get(0).getTipoPersona());
  }
}
