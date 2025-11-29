package com.example.ventasbolivar.service.serviceimpl;

import com.example.ventasbolivar.repository.DashboardRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DashboardServiceimplTest {

  @InjectMocks
  private DashboardServiceimpl dashboardService;

  @Mock
  private DashboardRepository repo;

  private String inicio;
  private String fin;

  private Map<String, Object> kpisRaw;
  private Map<String, Object> kpisAnteriorRaw;
  private Map<String, Object> resumenRaw;

  @BeforeEach
  void setup() {
    inicio = "2025-01-01";
    fin = "2025-01-10";

    kpisRaw = new HashMap<>();
    kpisRaw.put("ventas_totales", 1000.0);

    kpisAnteriorRaw = new HashMap<>();
    kpisAnteriorRaw.put("total", 500.0); // rango anterior

    resumenRaw = new HashMap<>();
    resumenRaw.put("usuarios", 20);
    resumenRaw.put("articulos", 100);
  }

  @Test
  void getDashboardData_DatosCorrectos() {

    List<Map<String, Object>> rendimiento = List.of(Map.of("dia", "2025-01-01", "total", 100));
    List<Map<String, Object>> categorias = List.of(Map.of("categoria", "Bebidas", "total", 300));
    List<Map<String, Object>> clientes = List.of(Map.of("cliente", "Juan", "total", 150));
    List<Map<String, Object>> ranking = List.of(Map.of("articulo", "Pizza", "ganancia", 200));
    List<Map<String, Object>> mapaCalor = List.of(Map.of("hora", "10:00", "total", 50));

    when(repo.obtenerKpis(inicio, fin)).thenReturn(kpisRaw);

    LocalDate start = LocalDate.parse(inicio); // 2025-01-01
    LocalDate end = LocalDate.parse(fin);      // 2025-01-10
    long dias = 10; // inclusive

    LocalDate inicioAnterior = start.minusDays(dias); // 2024-12-22
    LocalDate finAnterior = start.minusDays(1);       // 2024-12-31

    when(repo.obtenerVentasAnteriores(inicioAnterior.toString(), finAnterior.toString()))
     .thenReturn(kpisAnteriorRaw);

    when(repo.resumenSistema()).thenReturn(resumenRaw);
    when(repo.rendimientoVentas(inicio, fin)).thenReturn(rendimiento);
    when(repo.ventasPorCategoria(inicio, fin)).thenReturn(categorias);
    when(repo.topClientes(inicio, fin)).thenReturn(clientes);
    when(repo.rankingRentabilidad(inicio, fin)).thenReturn(ranking);
    when(repo.mapaCalor(inicio, fin)).thenReturn(mapaCalor);

    Map<String, Object> response = dashboardService.getDashboardData(inicio, fin);

    assertNotNull(response);
    assertTrue(response.containsKey("kpis"));
    assertTrue(response.containsKey("rendimientoVentas"));
    assertTrue(response.containsKey("ventasPorCategoria"));
    assertTrue(response.containsKey("topClientes"));
    assertTrue(response.containsKey("rankingRentabilidad"));
    assertTrue(response.containsKey("mapaCalorVentas"));
    assertTrue(response.containsKey("resumenSistema"));

    Map<String, Object> kpisResp = (Map<String, Object>) response.get("kpis");
    assertEquals(1000.0, (double) kpisResp.get("ventas_totales"));

    assertEquals(100.0, (double) kpisResp.get("ventas_comparativa"));

    assertEquals(rendimiento, response.get("rendimientoVentas"));
    assertEquals(categorias, response.get("ventasPorCategoria"));
    assertEquals(clientes, response.get("topClientes"));
    assertEquals(ranking, response.get("rankingRentabilidad"));
    assertEquals(mapaCalor, response.get("mapaCalorVentas"));

    Map<String, Object> respResumen = (Map<String, Object>) response.get("resumenSistema");
    assertEquals(20, respResumen.get("usuarios"));
    assertEquals(100, respResumen.get("articulos"));

    verify(repo).obtenerKpis(inicio, fin);
    verify(repo).obtenerVentasAnteriores(inicioAnterior.toString(), finAnterior.toString());
    verify(repo).resumenSistema();
    verify(repo).rendimientoVentas(inicio, fin);
    verify(repo).ventasPorCategoria(inicio, fin);
    verify(repo).topClientes(inicio, fin);
    verify(repo).rankingRentabilidad(inicio, fin);
    verify(repo).mapaCalor(inicio, fin);
  }
}
