/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.victor.bazarProyectoFinal.controller;


import com.victor.bazarProyectoFinal.dto.ProductoDTO;
import com.victor.bazarProyectoFinal.model.Producto;
import com.victor.bazarProyectoFinal.service.IProductoService;
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
public class ProductoController {
    
    @Autowired
    private IProductoService productoService;
    
    @GetMapping("/productos")
    @ResponseBody
    public List<ProductoDTO> listProductos(){
        return productoService.listProductos();
    }
    
    @PostMapping("/productos/crear")
    public String createProducto(@RequestBody Producto producto){
        productoService.createProducto(producto);
        return "Creado correctamente";
    }
    
    @PostMapping("/productos/listcrear")
    public String createListProducto(@RequestBody List<Producto> listProducto){
        productoService.createListProducto(listProducto);
        return "Creado correctamente";
    }
    
    @PutMapping("/productos/editar/{id_producto}")
    public String updateProducto(@PathVariable Long id_producto, @RequestBody Producto producto){
        productoService.updateProducto(id_producto, producto);
        return "Actualizado correctamente";
    }
    
    @DeleteMapping("/productos/eliminar/{id_producto}")
    public String deleteProducto(@PathVariable Long id_producto){
        productoService.deleteProducto(id_producto);
        return "Eliminado correctamente";
    }
    
    //consultas
    @GetMapping("/productos/falta_stock")
    public List<ProductoDTO> listLowStock(){
        return productoService.listLowStock();
    }
    
    
}