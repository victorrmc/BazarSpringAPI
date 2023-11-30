/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.victor.bazarProyectoFinal.service;

import com.victor.bazarProyectoFinal.dto.DtoVenta;
import com.victor.bazarProyectoFinal.dto.VentaCliente;
import com.victor.bazarProyectoFinal.model.Producto;
import com.victor.bazarProyectoFinal.model.Venta;
import com.victor.bazarProyectoFinal.model.Venta;
import com.victor.bazarProyectoFinal.repository.IVentaRepository;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VentaService implements IVentaService {

    @Autowired
    private IVentaRepository ventaRepo;
    
    
//    Nota: No es necesario para este requerimiento actualizar el stock de un venta (descontar)
//al realizar una venta, ni tampoco controlar si cuenta con la cantidad disponible para vender;
//sin embargo, se considerará como “plus” o extra (para el bonus del punto 8) si se desea
//implementar la funcionalidad.

    @Override
    public void createVenta(Venta venta) {
        ventaRepo.save(venta);
    }
    @Override
    public void createListVenta(List<Venta> listVenta) {
        ventaRepo.saveAll(listVenta);
    }

    @Override
    public List<Venta> listVentas() {
        return ventaRepo.findAll();
    }

    @Override
    public Venta searchVenta(Long id) {
        return ventaRepo.findById(id).orElse(null);
    }

    @Override
    public void deleteVenta(Long id) {
        ventaRepo.deleteById(id);
    }

//    Nota: No es necesario para este requerimiento actualizar el stock de un venta (descontar)
//al realizar una venta, ni tampoco controlar si cuenta con la cantidad disponible para vender;
//sin embargo, se considerará como “plus” o extra (para el bonus del punto 8) si se desea
//implementar la funcionalidad.
    @Override
    public void updateVenta(Long id, Venta newventa) {
        Venta venta = this.searchVenta(id);
        venta.setCodigo_venta(newventa.getCodigo_venta());
        venta.setFecha_venta(newventa.getFecha_venta());
        venta.setListaProductos(newventa.getListaProductos());
        venta.setUnCliente(newventa.getUnCliente());
        venta.setTotal(newventa.getTotal());

        this.createVenta(venta);
    }

//modificar
    @Override
    public List<Producto> listProductoByVenta(Long id) {
        Venta venta = this.searchVenta(id);
        System.out.println(venta.getListaProductos());
        System.out.println(venta.getListaProductos().toString());
        return venta.getListaProductos();
    }

    @Override
    public DtoVenta ventaByDay(LocalDate fecha_venta) {
        //Muestra las ventas por el dia
        List<Venta> listVenta = this.listVentas();
        
        double sumatoriaMonto = listVenta.stream()
        .filter(venta -> venta.getFecha_venta().equals(fecha_venta))
        .mapToDouble(Venta::getTotal)
        .sum();
        
        long cantidadVentas = listVenta.stream()
        .filter(venta -> venta.getFecha_venta().equals(fecha_venta))
        .count();
        
        DtoVenta dtoVenta = new DtoVenta();
        dtoVenta.setDineroTotal(sumatoriaMonto);
        dtoVenta.setVentasTotal(cantidadVentas);

        return dtoVenta;
    }

    @Override
    public VentaCliente clienteVentaMasAlta() {
        //Obtener el codigo_venta, el total, la cantidad de productos, el nombre del cliente y el
        //apellido del cliente de la venta con el monto más alto de todas.
        VentaCliente ventaCliente = new VentaCliente();
        List<Venta> listVenta = this.listVentas();
        Venta ventaMasAlta = listVenta.stream()
                .sorted((venta1, venta2)-> 
                        venta2.getTotal().compareTo(venta1.getTotal()
                        )).findFirst().orElse(null);
        if (ventaMasAlta != null) {
        ventaCliente.setCodigo_venta(ventaMasAlta.getCodigo_venta());
        ventaCliente.setTotal(ventaMasAlta.getTotal());
        ventaCliente.setCantidad_producto(ventaMasAlta.getListaProductos().size());
        ventaCliente.setNombre_cliente(ventaMasAlta.getUnCliente().getNombre());
        ventaCliente.setApellido_cliente(ventaMasAlta.getUnCliente().getApellido());
    }
        return ventaCliente;
    }

    

}
