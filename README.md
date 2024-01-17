<h1>Acerca de la aplicacion</h1>
La aplicacion es un servicio back API REST de to-do's que implementa un patron arquitectonico hexagonal y reactivo hecho con spring boot, spring webflux y RXJava.

La persistencia se realiza utilizando Spring JPA a una base de datos embebida en memoria llamada h2.

Adicionalmente se utilizan algunos patrones de diseño como lo son builder, singleton, inyeccion de dependencias, inversion de control, etc.

La aplicacion corre por el puerto 8080

A continuacion les dejo los endpoints con su respectivo body:

<h3>POST /todo - creacion de un todo</h3>

<code>
{
    "description": "Task 1",
    "checked": false
}
</code>

<h3>GET /todos - listar todos los todo's</h3>

<h3>GET /todo/{id} - filtrar un todo por el id</h3>

<h3>PUT /todo/{id} - Actualizar un todo</h3>
{
    "description": "Task 1 updated",
    "checked": true
}

<h3>DELETE /todo/{id} - Eliminar un todo</h3>

Cualquier duda o inconveniente, no duden en contactarme al correo andrescaza246@hotmail.com
