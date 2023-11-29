/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.victor.bazarProyectoFinal.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class VentaCliente {
    private Long codigo_venta;
    private Double total; 
    private int cantidad_producto;
    private String nombre_cliente;
    private String apellido_cliente;
    
}
