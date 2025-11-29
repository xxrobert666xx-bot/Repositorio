package com.example.ventasbolivar.repository;

import com.example.ventasbolivar.model.entity.DetalleVenta;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * DetallaVentaRepository.
 */
public interface DetalleVentaRepository extends JpaRepository<DetalleVenta, Integer> {
  /**
   * Obtener venta por id.
   */
  List<DetalleVenta> findByVentaId(Integer idventa);
}