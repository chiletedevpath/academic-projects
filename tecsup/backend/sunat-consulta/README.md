# Sunat-Consulta Microservice

Examen de microservicio desarrollado con **Spring Boot** para la consulta de RUC utilizando la API externa de Decolecta (SUNAT).

---

## 📌 Características Implementadas

- ✅ Consumo de API externa con **OpenFeign**
- ✅ Manejo de errores (4xx / 5xx)
- ✅ Persistencia con **JPA (H2)**
- ✅ Relación **Company 1..N Consulta**
- ✅ Cache simple de 10 minutos
- ✅ Manejo global de excepciones
- ✅ Uso de DTOs como `record`
- ✅ Enums con fallback seguro

---

## 1.- Tecnologías Utilizadas

| Tecnología        | Descripción                      |
|------------------|----------------------------------|
| Java 17+         | Lenguaje base                    |
| Spring Boot      | Framework principal              |
| Spring Data JPA  | Persistencia                     |
| OpenFeign        | Cliente HTTP declarativo         |
| H2 Database      | Base de datos en memoria         |
| Maven            | Gestión de dependencias          |
| Postman          | Pruebas de endpoints             |

---

## 2️.- Estructura del Proyecto

```text
com.demo.sunat
├─ config
├─ controller
├─ client
├─ dto
├─ entity
├─ enums
├─ exception
├─ mapper
├─ repository
└─ service
```

---

## 3️.- Configuración del Token (OBLIGATORIO)

El token **NO está hardcodeado**.  
Se configura mediante variable de entorno.

### application.properties

```properties
decolecta.base-url=https://api.decolecta.com
decolecta.token=${DECOLECTA_TOKEN}
```

### Configurar en Windows (PowerShell)

```powershell
setx DECOLECTA_TOKEN "TU_TOKEN_AQUI"
```

Cerrar y volver a abrir la terminal antes de ejecutar la aplicación.

---

## 4️.- ¿Cómo ejecutar el proyecto?

### 🔹 Opción 1 – Usando Maven Wrapper

**Windows**
```bash
mvnw.cmd spring-boot:run
```

**Linux / Mac**
```bash
./mvnw spring-boot:run
```

### 🔹 Opción 2 – Desde IntelliJ

Ejecutar la clase:

```
SunatConsultaApplication
```

---

## 5️.- Base de Datos

Se usa **H2 en memoria**.

### Configuración

```properties
spring.datasource.url=jdbc:h2:mem:sunatdb
spring.jpa.hibernate.ddl-auto=update
spring.h2.console.enabled=true
```

### Consola disponible en:

```
http://localhost:8080/h2-console
```

| Parámetro | Valor                |
|-----------|---------------------|
| JDBC URL  | jdbc:h2:mem:sunatdb |
| Usuario   | sa                  |
| Password  | (vacío)             |

---

## 6️.- Endpoints

### A. Consultar RUC

```
GET /api/sunat/ruc/{ruc}
```

Ejemplo:

```
GET http://localhost:8080/api/sunat/ruc/20100901481
```

### B. Historial de consultas

```
GET /api/sunat/ruc/{ruc}/consultas
```

---

## 7️.- Manejo de Errores

### a. Validación de RUC

Si el RUC no cumple `\d{11}`, devuelve:

```json
{
  "message": "RUC debe tener exactamente 11 dígitos"
}
```

### b. Error del proveedor (Feign)

Si el proveedor responde:

```json
{
  "message": "RUC no encontrado"
}
```

La API devuelve:

```json
{
  "message": "RUC no encontrado"
}
```

### c. Manejo global de excepciones

Se implementa `@RestControllerAdvice` para evitar el error default de Spring.

---

## 8️.- Persistencia

- Company con RUC único
- Relación 1..N con Consulta
- Registro de consultas SUCCESS y ERROR
- Transacciones manejadas en Service

---

## 9️.- Cache simple

Si una empresa fue consultada en los últimos 10 minutos:

- No se llama nuevamente al proveedor
- Se registra la consulta como SUCCESS (cache)

---

## 10.- Evidencias

En la carpeta `/evidencias` se incluyen:

- RUC válido (SUCCESS)
- RUC inválido (ERROR validación)
- Error del proveedor
- Evidencia de base de datos
- Colección Postman exportada

---

## 11.- Ejemplos curl

### ✔ RUC válido

```bash
curl http://localhost:8080/api/sunat/ruc/20100901481
```

### ❌ RUC inválido

```bash
curl http://localhost:8080/api/sunat/ruc/201257
```