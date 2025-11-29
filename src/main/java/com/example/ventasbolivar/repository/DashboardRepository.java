package com.example.ventasbolivar.repository;

import com.example.ventasbolivar.model.entity.Venta;
import java.util.List;
import java.util.Map;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * DashboardRepository.
 */
@Repository
public interface DashboardRepository extends JpaRepository<Venta, Long> {

  /**
   * Query para reportes.
   */
  @Query(value = """
   SELECT 
       IFNULL(SUM(v.total_venta), 0) AS ventas_totales,
       IFNULL(SUM(dv.cantidad * (dv.precio_venta - a.precio_compra)), 0) AS ganancia_bruta,
       IFNULL(AVG(v.total_venta), 0) AS ticket_promedio
   FROM venta v
   INNER JOIN detalle_venta dv ON v.idventa = dv.idventa
   INNER JOIN articulo a ON dv.idarticulo = a.idarticulo
   WHERE DATE(v.fecha_hora) BETWEEN :inicio AND :fin 
     AND v.estado='Aceptado'
      """, nativeQuery = true)
  Map<String, Object> obtenerKpis(@Param("inicio") String inicio,
                                  @Param("fin") String fin);

  /**
   * Query para reportes.
   */
  @Query(value = """
   SELECT IFNULL(SUM(total_venta), 0) AS total
   FROM venta 
   WHERE DATE(fecha_hora) BETWEEN :inicio AND :fin 
     AND estado='Aceptado'
      """, nativeQuery = true)
  Map<String, Object> obtenerVentasAnteriores(@Param("inicio") String inicio,
                                              @Param("fin") String fin);

  /**
   * Query para reportes.
   */
  @Query(value = """
   SELECT 
       DATE(fecha_hora) AS fecha,
       SUM(total_venta) AS total_ventas,
       COUNT(idventa) AS numero_pedidos,
       AVG(total_venta) AS ticket_promedio_dia
   FROM venta
   WHERE DATE(fecha_hora) BETWEEN :inicio AND :fin
     AND estado='Aceptado'
   GROUP BY DATE(fecha_hora)
   ORDER BY fecha ASC
      """, nativeQuery = true)
  List<Map<String, Object>> rendimientoVentas(@Param("inicio") String inicio,
                                              @Param("fin") String fin);

  /**
   * Query para reportes.
   */
  @Query(value = """
   SELECT 
       c.nombre AS categoria,
       SUM(dv.cantidad * dv.precio_venta) AS total_monetario,
       SUM(dv.cantidad) AS cantidad_vendida
   FROM detalle_venta dv
   INNER JOIN articulo a ON dv.idarticulo = a.idarticulo
   INNER JOIN categoria c ON a.idcategoria = c.idcategoria
   INNER JOIN venta v ON dv.idventa = v.idventa
   WHERE DATE(v.fecha_hora) BETWEEN :inicio AND :fin
     AND v.estado = 'Aceptado'
   GROUP BY c.nombre
   ORDER BY total_monetario DESC
   LIMIT 7
      """, nativeQuery = true)
  List<Map<String, Object>> ventasPorCategoria(@Param("inicio") String inicio,
                                               @Param("fin") String fin);

  /**
   * Query para reportes.
   */
  @Query(value = """
   SELECT 
       p.nombre,
       COUNT(v.idventa) AS frecuencia_compra,
       SUM(v.total_venta) AS total_gastado
   FROM venta v
   INNER JOIN persona p ON v.idcliente = p.idpersona
   WHERE DATE(v.fecha_hora) BETWEEN :inicio AND :fin
     AND v.estado = 'Aceptado'
   GROUP BY p.idpersona, p.nombre
   ORDER BY frecuencia_compra DESC, total_gastado DESC
   LIMIT 5
      """, nativeQuery = true)
  List<Map<String, Object>> topClientes(@Param("inicio") String inicio,
                                        @Param("fin") String fin);

  /**
   * Query para reportes.
   */
  @Query(value = """
   SELECT 
       a.nombre,
       SUM(dv.cantidad) AS unidades_vendidas,
       SUM(dv.cantidad * (dv.precio_venta - a.precio_compra)) AS ganancia_generada
   FROM detalle_venta dv
   INNER JOIN venta v ON dv.idventa = v.idventa
   INNER JOIN articulo a ON dv.idarticulo = a.idarticulo
   WHERE DATE(v.fecha_hora) BETWEEN :inicio AND :fin
     AND v.estado = 'Aceptado'
   GROUP BY a.idarticulo, a.nombre
   HAVING ganancia_generada IS NOT NULL
   ORDER BY ganancia_generada DESC
   LIMIT 5
      """, nativeQuery = true)
  List<Map<String, Object>> rankingRentabilidad(@Param("inicio") String inicio,
                                                @Param("fin") String fin);

  /**
   * Query para reportes.
   */
  @Query(value = """
   SELECT 
       WEEKDAY(fecha_hora) AS dia_semana,
       HOUR(fecha_hora) AS hora_dia,
       COUNT(idventa) AS total_ventas
   FROM venta
   WHERE DATE(fecha_hora) BETWEEN :inicio AND :fin
     AND estado='Aceptado'
   GROUP BY dia_semana, hora_dia
   ORDER BY dia_semana, hora_dia
      """, nativeQuery = true)
  List<Map<String, Object>> mapaCalor(@Param("inicio") String inicio,
                                      @Param("fin") String fin);

  /**
   * Query para reportes.
   */
  @Query(value = """
   SELECT 
       (SELECT COUNT(*) FROM articulo WHERE condicion=1) AS total_articulos,
       (SELECT COUNT(*) FROM usuario WHERE condicion=1) AS total_usuarios,
       (SELECT COUNT(*) FROM persona WHERE tipo_persona='Cliente') AS total_clientes,
       (SELECT COUNT(*) FROM persona WHERE tipo_persona='Proveedor') AS total_proveedores
      """, nativeQuery = true)
  Map<String, Object> resumenSistema();
}
