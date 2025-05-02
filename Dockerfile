# Usamos una imagen base de OpenJDK 21 para ejecutar el JAR
FROM openjdk:21-jdk-slim

# Establecemos el directorio de trabajo en el contenedor
WORKDIR /app

# Copiamos el archivo JAR al contenedor
COPY target/demo-0.0.1-SNAPSHOT.jar demo.jar

# Exponemos el puerto en el que la aplicación Spring Boot estará escuchando
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "demo.jar"]
