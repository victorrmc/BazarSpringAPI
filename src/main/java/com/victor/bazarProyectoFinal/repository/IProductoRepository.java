/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.victor.bazarProyectoFinal.repository;

import com.victor.bazarProyectoFinal.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author victo
 */
public interface IProductoRepository extends JpaRepository<Producto, Long> {
    
}
