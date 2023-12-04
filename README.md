# Bazar Proyecto Final

Este proyecto es una aplicación de gestión de un bazar. El proyecto está construido con Spring Boot y utiliza Spring Web para exponer endpoints RESTful.

## Clases

El proyecto consta de tres controladores principales:

1. `ClienteController`: Esta clase gestiona las operaciones relacionadas con los clientes. Proporciona endpoints para listar todos los clientes, crear un nuevo cliente, actualizar un cliente existente y eliminar un cliente.

2. `ProductoController`: Esta clase gestiona las operaciones relacionadas con los productos. Proporciona endpoints para listar todos los productos, crear un nuevo producto, actualizar un producto existente y eliminar un producto. También proporciona un endpoint para listar los productos con bajo stock.

3. `VentaController`: Esta clase gestiona las operaciones relacionadas con las ventas. Proporciona endpoints para listar todas las ventas, crear una nueva venta, actualizar una venta existente y eliminar una venta. También proporciona endpoints para listar los productos por venta, obtener las ventas por día y obtener el cliente con la venta más alta.

## Uso

Para utilizar los endpoints, puede enviar solicitudes HTTP a los siguientes endpoints:

- `/clientes`: GET para obtener una lista de todos los clientes.
- `/clientes/crear`: POST para crear un nuevo cliente.
- `/clientes/editar/{id_cliente}`: PUT para actualizar un cliente existente.
- `/clientes/eliminar/{id_cliente}`: DELETE para eliminar un cliente.

Los endpoints para `ProductoController` y `VentaController` funcionan de manera similar.

> [!IMPORTANT]  
> Hemos añadido una colección de Postman al proyecto para facilitar la prueba de los endpoints. Puedes encontrar la colección en el directorio `postman` en la raíz del proyecto.
