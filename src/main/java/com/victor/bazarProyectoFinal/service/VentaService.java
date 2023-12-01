/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.victor.bazarProyectoFinal.service;

import com.victor.bazarProyectoFinal.dto.ProductoDTO;
import com.victor.bazarProyectoFinal.dto.VentaSimple;
import com.victor.bazarProyectoFinal.dto.VentaCliente;
import com.victor.bazarProyectoFinal.dto.VentaDTO;
import com.victor.bazarProyectoFinal.model.Producto;
import com.victor.bazarProyectoFinal.model.Venta;
import com.victor.bazarProyectoFinal.model.Venta;
import com.victor.bazarProyectoFinal.repository.IProductoRepository;
import com.victor.bazarProyectoFinal.repository.IVentaRepository;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VentaService implements IVentaService {

    @Autowired
    private IVentaRepository ventaRepo;
    @Autowired
    private IProductoRepository productoRepo;

    @Override
    public void createVenta(Venta venta) {
        //Map donde va estar el id del producto y la cantidad
        Map<Long, Integer> cantidadProductos = new HashMap<>();
        List<String> productosSinStock = new ArrayList<>();
        // Calcula la cantidad total que se va a vender de cada producto
        for (Producto newproducto : venta.getListaProductos()) {
            cantidadProductos.put(newproducto.getCodigo_producto(),
                    cantidadProductos.getOrDefault(
                            newproducto.getCodigo_producto(), 0) + 1);
        }
        // Verifica la disponibilidad de cada producto
        for (Map.Entry<Long, Integer> entry : cantidadProductos.entrySet()) {
            Producto producto = productoRepo.findById(entry.getKey()).orElse(null);
            Double cantidadDisponible = producto.getCantidad_disponible();
            if (cantidadDisponible == null) {
                throw new RuntimeException("La cantidad disponible para el producto: " + producto.getNombre() + " es null");
            }
            if (cantidadDisponible < entry.getValue()) {
                productosSinStock.add(producto.getNombre());
            }
        }
        // Si todos los productos tienen suficiente stock, crea la venta y la cantidad disponible de los productos
        if (productosSinStock.isEmpty()) {
            // Actualiza la cantidad disponible de cada producto
            for (Map.Entry<Long, Integer> entry : cantidadProductos.entrySet()) {
                Producto producto = productoRepo.findById(entry.getKey()).orElse(null);
                Double cantidadDisponible = producto.getCantidad_disponible();
                producto.setCantidad_disponible(cantidadDisponible - entry.getValue());
                productoRepo.save(producto);
            }
            ventaRepo.save(venta);
        } else {
            // Si algún producto no tiene suficiente stock, lanza una excepción
            throw new RuntimeException("No hay suficiente stock para los productos: " + String.join(", ", productosSinStock));
        }
    }

    @Override
    public void createListVenta(List<Venta> listVenta) {
        ventaRepo.saveAll(listVenta);
    }

    @Override
    public List<VentaDTO> listVentas() {
        List<VentaDTO> listVentaDto = new ArrayList<>();
        List<Venta> listventa = ventaRepo.findAll();
        for (Venta venta : listventa) {
            VentaDTO ventaDto = new VentaDTO(venta);
            listVentaDto.add(ventaDto);
        }
        return listVentaDto;
    }

    @Override
    public VentaDTO searchVenta(Long id) {
        Venta venta = ventaRepo.findById(id).orElse(null);
        VentaDTO ventaDto = new VentaDTO(venta);
        return ventaDto;
    }

    @Override
    public void deleteVenta(Long id) {
        ventaRepo.deleteById(id);
    }

    public void updateVenta(Long id, Venta newventa) {
        Map<Long, Integer> cantidadProductos = new HashMap<>();
        List<String> productosSinStock = new ArrayList<>();

        // Calcula la cantidad total que se va a vender de cada producto
        for (Producto newproducto : newventa.getListaProductos()) {
            cantidadProductos.put(newproducto.getCodigo_producto(), cantidadProductos.getOrDefault(newproducto.getCodigo_producto(), 0) + 1);
        }

        // Verifica la disponibilidad de cada producto
        for (Map.Entry<Long, Integer> entry : cantidadProductos.entrySet()) {
            Producto producto = productoRepo.findById(entry.getKey()).orElse(null);
            Double cantidadDisponible = producto.getCantidad_disponible();
            if (cantidadDisponible == null || cantidadDisponible < entry.getValue()) {
                productosSinStock.add(producto.getNombre());
            }
        }

        // Si todos los productos tienen suficiente stock, actualiza la venta y la cantidad disponible de los productos
        if (productosSinStock.isEmpty()) {
            Venta venta = ventaRepo.findById(id).orElse(null);
            venta.setCodigo_venta(newventa.getCodigo_venta());
            venta.setFecha_venta(newventa.getFecha_venta());
            venta.setListaProductos(newventa.getListaProductos());
            venta.setUnCliente(newventa.getUnCliente());
            venta.setTotal(newventa.getTotal());

            // Actualiza la cantidad disponible de cada producto
            for (Map.Entry<Long, Integer> entry : cantidadProductos.entrySet()) {
                Producto producto = productoRepo.findById(entry.getKey()).orElse(null);
                producto.setCantidad_disponible(producto.getCantidad_disponible() - entry.getValue());
                productoRepo.save(producto);
            }

            ventaRepo.save(venta);
        } else {
            // Si algún producto no tiene suficiente stock, lanza una excepción
            throw new RuntimeException("No hay suficiente stock para los productos: " + String.join(", ", productosSinStock));
        }
    }

//modificar
    @Override
    public List<ProductoDTO> listProductoByVenta(Long id) {
        Venta venta = ventaRepo.findById(id).orElse(null);
        VentaDTO ventaDto = new VentaDTO(venta);
        return ventaDto.getListaProductos();
    }

    @Override
    public VentaSimple ventaByDay(LocalDate fecha_venta) {
        //Muestra las ventas por el dia
        List<Venta> listVenta = ventaRepo.findAll();

        double sumatoriaMonto = listVenta.stream()
                .filter(venta -> venta.getFecha_venta().equals(fecha_venta))
                .mapToDouble(Venta::getTotal)
                .sum();

        long cantidadVentas = listVenta.stream()
                .filter(venta -> venta.getFecha_venta().equals(fecha_venta))
                .count();

        VentaSimple dtoVenta = new VentaSimple();
        dtoVenta.setDineroTotal(sumatoriaMonto);
        dtoVenta.setVentasTotal(cantidadVentas);

        return dtoVenta;
    }

    @Override
    public VentaCliente clienteVentaMasAlta() {
        //Obtener el codigo_venta, el total, la cantidad de productos, el nombre del cliente y el
        //apellido del cliente de la venta con el monto más alto de todas.
        VentaCliente ventaCliente = new VentaCliente();
        List<Venta> listVenta = ventaRepo.findAll();
        Venta ventaMasAlta = listVenta.stream()
                .sorted((venta1, venta2)
                        -> venta2.getTotal().compareTo(venta1.getTotal()
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
