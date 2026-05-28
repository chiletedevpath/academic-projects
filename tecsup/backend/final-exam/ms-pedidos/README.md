# ms-pedidos

Microservicio backend desarrollado con Spring Boot para la gestión de pedidos mediante operaciones CRUD, validaciones, actualización de estados y despliegue en la nube utilizando PostgreSQL, Docker y Render.

---

# Tecnologías utilizadas

- Java 17
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Docker
- Render
- Maven
- Lombok
- Jakarta Validation

---

# Arquitectura del proyecto

El proyecto está organizado en capas:

- controller
- service
- repository
- dto
- entity
- exception

---

# Funcionalidades

- Registrar pedidos
- Listar pedidos
- Buscar pedido por ID
- Eliminar pedidos
- Actualizar estado de pedidos
- Validaciones de datos
- Manejo global de excepciones
- Cálculo automático del total del pedido

---

# Endpoints disponibles

## Crear pedido

POST

```http
/api/pedidos
```

---

## Listar pedidos

GET

```http
/api/pedidos
```

---

## Buscar pedido por ID

GET

```http
/api/pedidos/{id}
```

---

## Eliminar pedido

DELETE

```http
/api/pedidos/{id}
```

---

## Actualizar estado del pedido

PATCH

```http
/api/pedidos/{id}/estado
```

---

# Variables de entorno

```env
DB_URL=
DB_USERNAME=
DB_PASSWORD=
PORT=
```

---

# Docker

Construcción de imagen Docker:

```bash
docker build -t ms-pedidos .
```

Ejecución del contenedor:

```bash
docker run -p 8081:8081 ms-pedidos
```

---

# Despliegue

Microservicio desplegado en Render.

---

# Autor

Adrian Pisco | Full Stack - Tecsup