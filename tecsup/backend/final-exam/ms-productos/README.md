# ms-productos

Microservicio backend desarrollado con Spring Boot para gestionar productos. Forma parte del examen final de backend de Tecsup junto con `ms-pedidos`.

## Proposito

Permitir el registro, consulta, actualizacion y eliminacion logica de productos, aplicando validaciones y persistencia en PostgreSQL.

## Stack

- Java 17
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Maven
- Lombok
- Jakarta Validation
- Docker
- Render

## Arquitectura

El proyecto esta organizado por capas:

- `controller`: endpoints REST.
- `service`: reglas de negocio.
- `repository`: acceso a datos.
- `dto`: objetos de entrada y salida.
- `entity`: modelo persistente.
- `exception`: manejo de errores.

## Funcionalidades

- Registrar productos.
- Listar productos.
- Buscar producto por ID.
- Actualizar productos.
- Realizar eliminacion logica.
- Validar datos de entrada.
- Manejar excepciones de forma centralizada.

## Endpoints Principales

```http
POST   /api/productos
GET    /api/productos
GET    /api/productos/{id}
PUT    /api/productos/{id}
DELETE /api/productos/{id}
```

## Variables de Entorno

```env
DB_URL=
DB_USERNAME=
DB_PASSWORD=
PORT=
```

## Docker

```bash
docker build -t ms-productos .
docker run -p 8080:8080 ms-productos
```

## Estado

Proyecto academico finalizado.
