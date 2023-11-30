/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.victor.bazarProyectoFinal.service;

import com.victor.bazarProyectoFinal.dto.ProductoDTO;
import com.victor.bazarProyectoFinal.dto.ProductoDTO;
import com.victor.bazarProyectoFinal.model.Producto;
import com.victor.bazarProyectoFinal.model.Producto;
import com.victor.bazarProyectoFinal.repository.IProductoRepository;
import java.util.ArrayList;
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
    public List<ProductoDTO> listProductos() {
        List<ProductoDTO> listProductoDto = new ArrayList<>();
        List<Producto> listproducto = productoRepo.findAll();
        for (Producto producto : listproducto) {
            ProductoDTO productoDto = new ProductoDTO(producto);
            listProductoDto.add(productoDto);
        }
        return listProductoDto;
    }

    @Override
    public ProductoDTO searchProducto(Long id) {
        Producto producto = productoRepo.findById(id).orElse(null);
        ProductoDTO productoDto = new ProductoDTO(producto);
        return productoDto;
    }

    @Override
    public void deleteProducto(Long id) {
        productoRepo.deleteById(id);
    }

    @Override
    public void updateProducto(Long id, Producto newproducto) {
        Producto producto = productoRepo.findById(id).orElse(null);

        producto.setCodigo_producto(newproducto.getCodigo_producto());
        producto.setNombre(newproducto.getNombre());
        producto.setMarca(newproducto.getMarca());
        producto.setCantidad_disponible(newproducto.getCantidad_disponible());
        producto.setCosto(newproducto.getCosto());

        this.createProducto(producto);
    }

    @Override
    public List<ProductoDTO> listLowStock() {
        //Mostrar los productos con menos de 5 de stock
         List<ProductoDTO> listProductoDto = new ArrayList<>();
        List<Producto> listproducto = productoRepo.findAll().stream()
                .filter(producto -> producto.getCantidad_disponible() < 5)
                .collect(Collectors.toList());
        
        for (Producto producto : listproducto) {
            ProductoDTO productoDto = new ProductoDTO(producto);
            listProductoDto.add(productoDto);
        }
        return listProductoDto;
    }

}
