package com.victor.bazarProyectoFinal.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter  @Setter
public class Venta {
    
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private Long codigo_venta;
    private LocalDate fecha_venta;
    private Double total;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name="venta_producto",
        joinColumns=
            @JoinColumn(name="venta_id", referencedColumnName="codigo_venta", nullable = false),
        inverseJoinColumns=
            @JoinColumn(name="producto_id", referencedColumnName="codigo_producto",  nullable = false)
    )
    private List<Producto> listaProductos;
    
    @ManyToOne()
    @JoinColumn(name = "producto_idcliente")
    @JsonBackReference
    private Cliente unCliente;
    
}