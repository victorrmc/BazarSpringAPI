/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.victor.bazarProyectoFinal.service;

import com.victor.bazarProyectoFinal.model.Cliente;
import com.victor.bazarProyectoFinal.repository.IClienteRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService implements IClienteService {
    
    @Autowired
    private IClienteRepository clienteRepo;
    

    @Override
    public void createCliente(Cliente cliente) {
        clienteRepo.save(cliente);
    }
    
    @Override
    public void createListCliente(List<Cliente> listCliente) {
        clienteRepo.saveAll(listCliente);
    }

    @Override
    public List<Cliente> listClientes() {
        return clienteRepo.findAll();
    }

    @Override
    public Cliente searchCliente(Long id) {
        return clienteRepo.findById(id).orElse(null);
    }

    @Override
    public void deleteCliente(Long id) {
        clienteRepo.deleteById(id);
    }

    @Override
    public void updateCliente(Long id, Cliente newcliente) {
       Cliente cliente = this.searchCliente(id);
       
       cliente.setId_cliente(newcliente.getId_cliente());
       cliente.setNombre(newcliente.getNombre());
       cliente.setApellido(newcliente.getApellido());
       cliente.setDni(newcliente.getDni());
       
       this.createCliente(cliente);
    }
    
}
