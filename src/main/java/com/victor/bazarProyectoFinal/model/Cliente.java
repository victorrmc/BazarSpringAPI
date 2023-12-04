package com.victor.bazarProyectoFinal.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.time.LocalDate;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter  @Setter
public class Cliente {
    
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private Long id_cliente;
    private String nombre;
    private String apellido;
    private String dni;

    @OneToMany(mappedBy="unCliente")
    @JsonManagedReference
    @JsonIgnore
    private List<Venta> listClienteVenta;

    public Cliente() {
    }

    public Cliente(Long id_cliente, String nombre, String apellido, String dni, List<Venta> listClienteVenta) {
        this.id_cliente = id_cliente;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.listClienteVenta = listClienteVenta;
    }
    
    
}