# ms-pedidos

Microservicio backend desarrollado con Spring Boot para gestionar pedidos. Forma parte del examen final de backend de Tecsup junto con `ms-productos`.

## Proposito

Permitir el registro, consulta, eliminacion y actualizacion de estado de pedidos, aplicando validaciones y calculando automaticamente el total del pedido.

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

- Registrar pedidos.
- Listar pedidos.
- Buscar pedido por ID.
- Eliminar pedidos.
- Actualizar estado de pedidos.
- Validar datos de entrada.
- Manejar excepciones de forma centralizada.
- Calcular automaticamente el total del pedido.

## Endpoints Principales

```http
POST   /api/pedidos
GET    /api/pedidos
GET    /api/pedidos/{id}
DELETE /api/pedidos/{id}
PATCH  /api/pedidos/{id}/estado
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
docker build -t ms-pedidos .
docker run -p 8081:8081 ms-pedidos
```

## Estado

Proyecto academico finalizado.
