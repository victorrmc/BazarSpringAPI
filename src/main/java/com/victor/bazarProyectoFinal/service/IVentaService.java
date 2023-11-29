/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.victor.bazarProyectoFinal.service;

import com.victor.bazarProyectoFinal.dto.DtoVenta;
import com.victor.bazarProyectoFinal.dto.VentaCliente;
import com.victor.bazarProyectoFinal.model.Producto;
import com.victor.bazarProyectoFinal.model.Venta;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author victo
 */
public interface IVentaService {
    
    //CRUD
    public void createVenta(Venta venta);
    public void createListVenta(List<Venta> listVenta);
    public List<Venta> listVentas();
    public Venta searchVenta(Long id);
    public void deleteVenta(Long id);
    public void updateVenta(Long id, Venta venta);
    
    //consultas
    public List<Producto> listProductoByVenta(Long id);
    public DtoVenta ventaByDay(LocalDate fecha_venta);
    public VentaCliente clienteVentaMasAlta();
    
}
