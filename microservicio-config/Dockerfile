# Usar una imagen base de OpenJDK
FROM openjdk:17-jdk-slim

# Volumen temporal para aplicaciones
VOLUME /tmp

# ARG para el JAR que se va a copiar
ARG JAR_FILE=target/microservicio-config-0.0.1-SNAPSHOT.jar

# Copiar el JAR compilado
COPY ${JAR_FILE} app.jar

# Copiar los archivos de configuración externos
COPY src/main/resources/configuracion /configuracion

# Exponer el puerto por defecto de Spring Boot
EXPOSE 8888

# Comando de ejecución del contenedor
ENTRYPOINT ["java", "-jar", "/app.jar"]
