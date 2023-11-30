/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.victor.bazarProyectoFinal.dto;

import com.victor.bazarProyectoFinal.model.Producto;
import com.victor.bazarProyectoFinal.model.Venta;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ProductoDTO {
    private Long codigo_producto;
    private String nombre;
    private String marca;
    private Double costo;
    private Double cantidad_disponible;
    private List<VentaDTO> listVenta;

    public ProductoDTO() {
    }

    public ProductoDTO(Producto pro) {
       this.cantidad_disponible = pro.getCantidad_disponible();
       this.codigo_producto = pro.getCodigo_producto();
       this.costo = pro.getCosto();
       this.marca = pro.getMarca();
       this.nombre = pro.getNombre();
        for (Venta ven : pro.getListVenta()) {
            VentaDTO venDto = new VentaDTO();
            venDto.setCliente(ven.getUnCliente().getId_cliente());
            venDto.setCodigo_venta(ven.getCodigo_venta());
            venDto.setFecha_venta(ven.getFecha_venta());
            venDto.setTotal(ven.getTotal());
        }
    }
}
