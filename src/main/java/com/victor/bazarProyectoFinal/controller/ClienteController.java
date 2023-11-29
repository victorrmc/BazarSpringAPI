/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.victor.bazarProyectoFinal.controller;

import com.victor.bazarProyectoFinal.model.Cliente;
import com.victor.bazarProyectoFinal.service.IClienteService;
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
public class ClienteController {
    
    @Autowired
    private IClienteService clienteService;
    
    @GetMapping("/clientes")
    @ResponseBody
    public List<Cliente> listClientes(){
        return clienteService.listClientes();
    }
    
    @PostMapping("/clientes/crear")
    public String createCliente(@RequestBody Cliente cliente){
        clienteService.createCliente(cliente);
        return "Creado correctamente";
    }
    
    @PostMapping("/clientes/listcrear")
    public String createListCliente(@RequestBody List<Cliente> listCliente){
        clienteService.createListCliente(listCliente);
        return "Creado correctamente";
    }
    
    @PutMapping("/clientes/editar/{id_cliente}")
    public String updateCliente(@PathVariable Long id_cliente, @RequestBody Cliente cliente){
        clienteService.updateCliente(id_cliente, cliente);
        return "Actualizado correctamente";
    }
    
    @DeleteMapping("/clientes/eliminar/{id_cliente}")
    public String deleteCliente(@PathVariable Long id_cliente){
        clienteService.deleteCliente(id_cliente);
        return "Eliminado correctamente";
    }
    
    
}
