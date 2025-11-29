package com.example.ventasbolivar.repository;

import com.example.ventasbolivar.model.entity.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * VentaRepository.
 */
public interface VentaRepository extends JpaRepository<Venta, Integer> {

  /**
   * Query para numero de comprobante.
   */
  @Query(value = "SELECT MAX(CAST(num_comprobante AS UNSIGNED)) FROM "
      + "venta WHERE serie_comprobante = ?1", nativeQuery = true)
  Integer findMaxNumComprobanteBySerie(String serieComprobante);
}