package com.example.ventasbolivar.service;


import com.example.ventasbolivar.model.dto.ArticuloVentaResponse;
import com.example.ventasbolivar.model.dto.DetalleVentaResponse;
import com.example.ventasbolivar.model.dto.SelectOptionDto;
import com.example.ventasbolivar.model.dto.SerieNumeroResponse;
import com.example.ventasbolivar.model.dto.VentaRequest;
import com.example.ventasbolivar.model.dto.VentaResponse;
import java.util.List;

/**
 * VentaService.
 */
public interface VentaService {

  /**
   * Listar ventas.
   */
  List<VentaResponse> listar();

  /**
   * Obtener una venta por id.
   */
  VentaResponse obtener(Integer id);

  /**
   * Crear una venta.
   */
  VentaResponse crear(VentaRequest request);

  /**
   * Anular una venta.
   */
  VentaResponse anular(Integer id);

  /**
   * Listar clientes.
   */
  List<SelectOptionDto> selectClientes();

  /**
   * Listar articulos.
   */
  List<ArticuloVentaResponse> listarArticulosActivosVenta();

  /**
   * Caluclar numero de serie.
   */
  SerieNumeroResponse nextSerieNumero(String tipo);

  /**
   * Listar detalle de una venta.
   */
  List<DetalleVentaResponse> listarDetalle(Integer idVenta);
}