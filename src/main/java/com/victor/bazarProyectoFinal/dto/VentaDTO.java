/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.victor.bazarProyectoFinal.dto;

import com.victor.bazarProyectoFinal.model.Cliente;
import com.victor.bazarProyectoFinal.model.Producto;
import com.victor.bazarProyectoFinal.model.Venta;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class VentaDTO {
    private Long codigo_venta;
    private LocalDate fecha_venta;
    private Double total;
    private List<ProductoDTO> listaProductos = new ArrayList();
    private Long cliente;

    public VentaDTO() {
    }

    public VentaDTO(Venta venta) {
        this.codigo_venta = venta.getCodigo_venta();
        this.fecha_venta = venta.getFecha_venta();
        this.total = venta.getTotal();
        this.cliente = venta.getUnCliente().getId_cliente();
        for (Producto produc : venta.getListaProductos()) {
            ProductoDTO productoDto = new ProductoDTO();
            productoDto.setCodigo_producto(produc.getCodigo_producto());
            productoDto.setCantidad_disponible(produc.getCantidad_disponible());
            productoDto.setCosto(produc.getCosto());
            productoDto.setMarca(produc.getMarca());
            productoDto.setNombre(produc.getNombre());
            listaProductos.add(productoDto);
        }
    }
    
    
}
