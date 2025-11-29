package com.example.ventasbolivar.service.serviceimpl;

import com.example.ventasbolivar.model.dto.PermisoResponse;
import com.example.ventasbolivar.repository.PermisoRepository;
import com.example.ventasbolivar.service.PermisoService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * PermisoServiceImpl.
 */
@Service
@RequiredArgsConstructor
public class PermisoServiceimpl implements PermisoService {

  private static final Logger log = LoggerFactory.getLogger(PermisoServiceimpl.class);

  private final PermisoRepository repo;

  @Override
  public List<PermisoResponse> listar() {
    log.info("Listando todos los permisos");

    List<PermisoResponse> lista = repo.findAll()
        .stream()
        .map(p -> new PermisoResponse(
        p.getId(),
        p.getNombre()
        ))
        .toList();

    log.info("Total permisos encontrados: {}", lista.size());

    return lista;
  }
}