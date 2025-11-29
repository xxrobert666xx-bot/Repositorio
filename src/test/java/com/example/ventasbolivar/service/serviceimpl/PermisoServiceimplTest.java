package com.example.ventasbolivar.service.serviceimpl;

import com.example.ventasbolivar.model.dto.PermisoResponse;
import com.example.ventasbolivar.model.entity.Permiso;
import com.example.ventasbolivar.repository.PermisoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PermisoServiceimplTest {

  @Mock
  private PermisoRepository repo;

  @InjectMocks
  private PermisoServiceimpl service;

  @Test
  void listar() {
    Permiso p1 = new Permiso(1, "Almacén");
    Permiso p2 = new Permiso(2, "Ventas");

    when(repo.findAll()).thenReturn(List.of(p1, p2));

    List<PermisoResponse> resp = service.listar();

    assertEquals(2, resp.size());
    assertEquals("Almacén", resp.get(0).getNombre());

    verify(repo).findAll();
  }
}
