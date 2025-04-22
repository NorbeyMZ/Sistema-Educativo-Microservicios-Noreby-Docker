# Usar una imagen base de OpenJDK
FROM openjdk:17-jdk-slim

# Volumen temporal para aplicaciones
VOLUME /tmp

# Definir el archivo JAR que se va a copiar al contenedor
ARG JAR_FILE=target/microservicio-gateway-0.0.1-SNAPSHOT.jar

# Copiar el archivo JAR al contenedor
COPY ${JAR_FILE} app.jar

# Definir el comando de ejecución del contenedor
ENTRYPOINT ["java", "-jar", "/app.jar"]
