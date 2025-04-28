# Sistema Educativo - Microservicios con Spring Boot

Este proyecto es un sistema educativo distribuido desarrollado con arquitectura de microservicios, usando **Spring Boot**, **Spring Cloud**, **Docker**, **Eureka**, y **Config Server**.



![image](https://github.com/user-attachments/assets/fab53e7c-4b54-444b-92a9-64f9d9d72eac)


---

## Microservicios

- `microservicio-estudiantes`: Gestión de estudiantes.
- `usuario-servicio`: Registro y autenticación de usuarios.
- `microservicio-asignaturas`: Gestión de materias y relación con estudiantes.
- `microservicio-config`: Centralización de configuraciones.
- `microservicio-gateway`: Puerta de entrada al sistema (API Gateway).
- `microservicio-eureka`: Descubrimiento de servicios (Service Discovery).

---

## Tecnologías principales

- Java 17
- Spring Boot 3
- Spring Cloud
- Eureka Server
- Spring Cloud Config
- Feign Client
- Docker y Docker Compose
- GitHub Actions (para integración continua)

---

## Cómo levantar el proyecto

1. Asegúrate de tener Docker y Docker Compose instalados.
2. En la raíz del proyecto, ejecuta:

```bash
docker-compose up --build


