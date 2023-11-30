/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.victor.bazarProyectoFinal.service;

import com.victor.bazarProyectoFinal.dto.ProductoDTO;
import com.victor.bazarProyectoFinal.model.Producto;
import java.util.List;

/**
 *
 * @author victo
 */
public interface IProductoService {
    
    //CRUD
    public void createProducto(Producto produ);
    public void createListProducto(List<Producto> listProducto);
    public List<ProductoDTO> listProductos();
    public ProductoDTO searchProducto(Long id);
    public void deleteProducto(Long id);
    public void updateProducto(Long id, Producto produ);
    
    //consultas
    public List<ProductoDTO> listLowStock();
    
    
}
