package com.victor.bazarProyectoFinal;

import com.victor.bazarProyectoFinal.dto.ProductoDTO;
import com.victor.bazarProyectoFinal.dto.VentaDTO;
import com.victor.bazarProyectoFinal.model.Cliente;
import com.victor.bazarProyectoFinal.model.Producto;
import com.victor.bazarProyectoFinal.model.Venta;
import com.victor.bazarProyectoFinal.repository.IProductoRepository;
import com.victor.bazarProyectoFinal.service.IClienteService;
import com.victor.bazarProyectoFinal.service.IProductoService;
import com.victor.bazarProyectoFinal.service.IVentaService;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BazarProyectoFinalApplicationTests {

    @Autowired
    private IProductoService productoService;
    @Autowired
    private IClienteService ClienteService;
    @Autowired
    private IVentaService VentaService;

//    @BeforeEach
//    public void setUp(){
//        Cliente cli = new Cliente(2L, "Marrero", "32222W", "Victor", null);
//        ClienteService.createCliente(cli);
//
//        Producto pro = new Producto(2L, "carhartt", "chaqueta", 10.00, 119.99, null);
//        productoService.createProducto(pro);
//    
//        Venta ven = new Venta(2L, LocalDate.now(), 200.00, null, null);
//        VentaService.createVenta(ven);
//    }
    @BeforeAll
    public static void init(){
        System.out.println("BeforeAll init() method called");
        
    }

    @BeforeEach
    public void initEach(){
        System.out.println("BeforeEach initEach() method called");
        Producto pro = new Producto(1L, "carhartt", "chaqueta", 10.00, 119.99, null);
        productoService.createProducto(pro);
        
        Cliente cli = new Cliente(1L, "Marrero", "32222W", "Victor", null);
        ClienteService.createCliente(cli);
        
        List<Producto> listpro = new ArrayList<>();
        listpro.add(pro);
        Venta ven = new Venta(1L, LocalDate.now(), 200.00, listpro, cli);
        VentaService.createVenta(ven);
        
    }
    @DisplayName("Test crear producto y listarlo")
    @Test
    public void testProducto() {
        
         
        List<ProductoDTO> productos = productoService.listProductos();
        // Asegúrate de que la lista de productos no está vacía
        assertFalse(productos.isEmpty());
    }

    //cliente
    @DisplayName("Test crear cliente y listarlo")
    @Test
    public void testCliente() {
        
        List<Cliente> clientes = ClienteService.listClientes();
        // Asegúrate de que la lista de clientes no está vacía
        assertFalse(clientes.isEmpty());
    }
    
    @DisplayName("Test crear venta y listarla")
    @Test
    public void testVenta() {
        List<VentaDTO> ventas = VentaService.listVentas();
        // Asegúrate de que la lista de clientes no está vacía
        assertFalse(ventas.isEmpty());
    }
    
}
