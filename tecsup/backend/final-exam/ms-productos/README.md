# ms-productos

Microservicio backend desarrollado con Spring Boot para la gestión de productos mediante operaciones CRUD, utilizando PostgreSQL, Spring Data JPA, Docker y despliegue en Render.

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

- Registrar productos
- Listar productos
- Buscar producto por ID
- Actualizar productos
- Eliminación lógica de productos
- Validaciones de datos
- Manejo global de excepciones

---

# Endpoints disponibles

## Crear producto

POST

```http
/api/productos
```

---

## Listar productos

GET

```http
/api/productos
```

---

## Buscar producto por ID

GET

```http
/api/productos/{id}
```

---

## Actualizar producto

PUT

```http
/api/productos/{id}
```

---

## Eliminar producto

DELETE

```http
/api/productos/{id}
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
docker build -t ms-productos .
```

Ejecución del contenedor:

```bash
docker run -p 8080:8080 ms-productos
```

---

# Despliegue

Microservicio desplegado en Render.

---

# Autor

Adrian Pisco | 
Full Stack - Tecsup