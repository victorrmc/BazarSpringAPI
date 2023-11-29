/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.victor.bazarProyectoFinal.service;

import com.victor.bazarProyectoFinal.model.Producto;
import com.victor.bazarProyectoFinal.repository.IProductoRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductoService implements IProductoService {

    @Autowired
    private IProductoRepository productoRepo;

    @Override
    public void createProducto(Producto producto) {
        productoRepo.save(producto);
    }

    @Override
    public void createListProducto(List<Producto> listProducto) {
        productoRepo.saveAll(listProducto);
    }

    @Override
    public List<Producto> listProductos() {
        return productoRepo.findAll();
    }

    @Override
    public Producto searchProducto(Long id) {
        return productoRepo.findById(id).orElse(null);
    }

    @Override
    public void deleteProducto(Long id) {
        productoRepo.deleteById(id);
    }

    @Override
    public void updateProducto(Long id, Producto newproducto) {
        Producto producto = this.searchProducto(id);

        producto.setCodigo_producto(newproducto.getCodigo_producto());
        producto.setNombre(newproducto.getNombre());
        producto.setMarca(newproducto.getMarca());
        producto.setCantidad_disponible(newproducto.getCantidad_disponible());
        producto.setCosto(newproducto.getCosto());

        this.createProducto(producto);
    }

    @Override
    public List<Producto> listLowStock() {
        //Mostrar los productos con menos de 5 de stock
        return this.listProductos().stream()
                .filter(producto -> producto.getCantidad_disponible() < 5)
                .collect(Collectors.toList());
    }

}
