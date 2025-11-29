package com.example.ventasbolivar.service.serviceimpl;

import com.example.ventasbolivar.repository.DashboardRepository;
import com.example.ventasbolivar.service.DashboardService;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * DashboardServiceImpl.
 */
@Service
public class DashboardServiceimpl implements DashboardService {

  private static final Logger log = LoggerFactory.getLogger(DashboardServiceimpl.class);

  private final DashboardRepository repo;

  /**
   * Dashboard.
   */
  public DashboardServiceimpl(DashboardRepository repo) {
    this.repo = repo;
  }

  @Override
  public Map<String, Object> getDashboardData(String inicio, String fin) {

    log.info("Generando dashboard desde {} hasta {}", inicio, fin);

    LocalDate start = LocalDate.parse(inicio);
    LocalDate end = LocalDate.parse(fin);

    long diasRango = ChronoUnit.DAYS.between(start, end) + 1;
    log.debug("Rango de días calculado: {}", diasRango);

    LocalDate inicioAnterior = start.minusDays(diasRango);
    LocalDate finAnterior = start.minusDays(1);

    log.info("Calculando rango anterior desde {} hasta {}", inicioAnterior, finAnterior);

    Map<String, Object> kpisRaw = repo.obtenerKpis(inicio, fin);
    Map<String, Object> kpisAnteriorRaw = repo.obtenerVentasAnteriores(
        inicioAnterior.toString(), finAnterior.toString());

    log.debug("KPIs actuales obtenidos: {}", kpisRaw);
    log.debug("KPIs anteriores obtenidos: {}", kpisAnteriorRaw);

    double ventasActuales = Double.parseDouble(kpisRaw.get("ventas_totales").toString());
    double ventasAnteriores = Double.parseDouble(kpisAnteriorRaw.get("total").toString());

    log.info("Ventas actuales: {}, ventas anteriores: {}", ventasActuales, ventasAnteriores);

    double comparativa = 0;
    if (ventasAnteriores > 0) {
      comparativa = ((ventasActuales - ventasAnteriores) / ventasAnteriores) * 100;
    } else if (ventasActuales > 0) {
      comparativa = 100;
    }

    log.info("Comparativa de ventas calculada: {}%", comparativa);

    Map<String, Object> kpis = new HashMap<>(kpisRaw);

    kpis.put("ventas_comparativa", comparativa);

    log.info("Obteniendo datos adicionales del dashboard...");

    List<Map<String, Object>> rendimientoRaw = repo.rendimientoVentas(inicio, fin);
    List<Map<String, Object>> categoriasRaw = repo.ventasPorCategoria(inicio, fin);
    List<Map<String, Object>> clientesRaw = repo.topClientes(inicio, fin);
    List<Map<String, Object>> rankingRaw = repo.rankingRentabilidad(inicio, fin);
    List<Map<String, Object>> mapaCalorRaw = repo.mapaCalor(inicio, fin);
    Map<String, Object> resumenRaw = repo.resumenSistema();
    Map<String, Object> resumen = new HashMap<>(resumenRaw);

    log.debug("Rendimiento ventas: {}", rendimientoRaw.size());
    log.debug("Ventas por categoría: {}", categoriasRaw.size());
    log.debug("Top clientes: {}", clientesRaw.size());
    log.debug("Ranking productos rentables: {}", rankingRaw.size());
    log.debug("Mapa de calor: {}", mapaCalorRaw.size());
    log.debug("Resumen sistema: {}", resumen);

    Map<String, Object> response = new HashMap<>();
    response.put("kpis", kpis);
    response.put("rendimientoVentas", rendimientoRaw);
    response.put("ventasPorCategoria", categoriasRaw);
    response.put("topClientes", clientesRaw);
    response.put("rankingRentabilidad", rankingRaw);
    response.put("mapaCalorVentas", mapaCalorRaw);
    response.put("resumenSistema", resumen);

    log.info("Dashboard generado correctamente.");

    return response;
  }
}