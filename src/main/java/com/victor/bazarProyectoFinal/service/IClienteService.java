/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.victor.bazarProyectoFinal.service;

import com.victor.bazarProyectoFinal.model.Cliente;
import java.util.List;

/**
 *
 * @author victo
 */
public interface IClienteService {
    
    //CRUD
    public void createCliente(Cliente cliente);
    public void createListCliente(List<Cliente> listCliente);
    public List<Cliente> listClientes();
    public Cliente searchCliente(Long id);
    public void deleteCliente(Long id);
    public void updateCliente(Long id, Cliente cliente);
    
}
