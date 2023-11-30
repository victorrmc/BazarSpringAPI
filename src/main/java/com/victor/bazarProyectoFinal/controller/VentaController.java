/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.victor.bazarProyectoFinal.controller;

import com.victor.bazarProyectoFinal.dto.ProductoDTO;
import com.victor.bazarProyectoFinal.dto.VentaSimple;
import com.victor.bazarProyectoFinal.dto.VentaCliente;
import com.victor.bazarProyectoFinal.dto.VentaDTO;
import com.victor.bazarProyectoFinal.model.Producto;
import com.victor.bazarProyectoFinal.model.Venta;
import com.victor.bazarProyectoFinal.service.IVentaService;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VentaController {
    
    @Autowired
    private IVentaService ventaService;
    
    @GetMapping("/ventas")
    @ResponseBody
    public List<VentaDTO> listVentas(){
        return ventaService.listVentas();
    }
    
    @PostMapping("/ventas/crear")
    public String createVenta(@RequestBody Venta venta){
        ventaService.createVenta(venta);
        return "Creado correctamente";
    }
    
    @PostMapping("/ventas/listcrear")
    public String createListVenta(@RequestBody List<Venta> listVenta){
        ventaService.createListVenta(listVenta);
        return "Creado correctamente";
    }
    
    @PutMapping("/ventas/editar/{id_venta}")
    public String updateVenta(@PathVariable Long id_venta, @RequestBody Venta venta){
        ventaService.updateVenta(id_venta, venta);
        return "Actualizado correctamente";
    }
    
    @DeleteMapping("/ventas/eliminar/{id_venta}")
    public String deleteVenta(@PathVariable Long id_venta){
        ventaService.deleteVenta(id_venta);
        return "Eliminado correctamente";
    }
    //consultas
    @GetMapping("/ventas/productos/{codigo_venta}")
    public List<ProductoDTO> listProductoByVenta(@PathVariable Long codigo_venta){
        return ventaService.listProductoByVenta(codigo_venta);
    }
    
    @GetMapping("/ventas/{fecha_venta}")
    public VentaSimple ventaByDay(@PathVariable LocalDate fecha_venta){
        return ventaService.ventaByDay(fecha_venta);
    }
    
    @GetMapping("/ventas/mayor_venta")
    public VentaCliente clienteVentaMasAlta(){
        return ventaService.clienteVentaMasAlta();
    }

    
    
}