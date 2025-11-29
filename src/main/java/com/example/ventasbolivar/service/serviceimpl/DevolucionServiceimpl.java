package com.example.ventasbolivar.service.serviceimpl;

import com.example.ventasbolivar.model.dto.DevolucionRequest;
import com.example.ventasbolivar.model.dto.DevolucionResponse;
import com.example.ventasbolivar.model.entity.Articulo;
import com.example.ventasbolivar.model.entity.Devolucion;
import com.example.ventasbolivar.repository.ArticuloRepository;
import com.example.ventasbolivar.repository.DevolucionRepository;
import com.example.ventasbolivar.service.DevolucionService;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * DevolucionServiceImpl.
 */
@Service
public class DevolucionServiceimpl implements DevolucionService {

  private final DevolucionRepository devolucionRepository;
  private final ArticuloRepository articuloRepository;

  /**
   * DevolucionServiceimpl.
   */
  public DevolucionServiceimpl(DevolucionRepository devolucionRepository,
                               ArticuloRepository articuloRepository) {
    this.devolucionRepository = devolucionRepository;
    this.articuloRepository = articuloRepository;
  }

  @Override
  public DevolucionResponse registrarDevolucion(DevolucionRequest request) {

    Devolucion dev = new Devolucion();
    dev.setIdventa(request.getIdventa());
    dev.setIdarticulo(request.getIdarticulo());
    dev.setCantidad(request.getCantidad());
    dev.setMotivo(request.getMotivo());
    dev.setFechaDevolucion(LocalDateTime.now());

    dev = devolucionRepository.save(dev);

    Articulo articulo = articuloRepository.findById(request.getIdarticulo())
        .orElseThrow(() -> new RuntimeException("Artículo no encontrado"));

    articulo.setStock(articulo.getStock() + request.getCantidad());
    articuloRepository.save(articulo);

    return entityToResponse(dev);
  }

  @Override
  public List<DevolucionResponse> listarDevoluciones() {
    return devolucionRepository.findAll()
     .stream()
     .map(this::entityToResponse)
     .collect(Collectors.toList());
  }

  @Override
  public DevolucionResponse obtenerPorId(Integer id) {
    Devolucion dev = devolucionRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Devolución no encontrada"));
    return entityToResponse(dev);
  }

  private DevolucionResponse entityToResponse(Devolucion dev) {
    DevolucionResponse res = new DevolucionResponse();
    res.setIddevolucion(dev.getIddevolucion());
    res.setIdventa(dev.getIdventa());
    res.setIdarticulo(dev.getIdarticulo());
    res.setCantidad(dev.getCantidad());
    res.setMotivo(dev.getMotivo());
    res.setFechaDevolucion(dev.getFechaDevolucion());
    return res;
  }
}
