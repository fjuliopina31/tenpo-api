# 🧾 Tenpo API - Despliegue con Docker Compose desde Docker Hub

imagen docker hub
-- Se obtiene la imagen de Docker Hub
docker pull fjuliopina33/tenpo-api

Este proyecto despliega una API Java Spring Boot usando una imagen almacenada en Docker Hub y una base de datos PostgreSQL. Se utiliza `docker-compose` para orquestar los servicios.

--- 

## 📦 Requisitos

- Docker instalado
- Docker Compose instalado
- Conexión a internet (para descargar la imagen desde Docker Hub)

---

## 🚀 Cómo levantar el entorno

1. Clona o crea un directorio y dentro coloca el archivo `docker-compose.yml` con el siguiente contenido:

```yaml
version: '3.8'

services:

  db:
    image: postgres:15
    container_name: tenpo_db_postgres
    environment:
      POSTGRES_DB: tenpoDB
      POSTGRES_USER: tenpo1
      POSTGRES_PASSWORD: tenpo1*
    volumes:
      - pgdata:/var/lib/postgresql/data
    ports:
      - "5432:5432"
    networks:
      - tenpo-net

  app:
    image: fjuliopina33/tenpo-api
    container_name: tenpo-app
    depends_on:
      - db
    command: ["./wait-for-it.sh", "db:5432", "--", "java", "-jar", "/app/demo.jar"]
    environment:
      SPRING_DATASOURCE_URL: jdbc:postgresql://db:5432/tenpoDB
      SPRING_DATASOURCE_USERNAME: tenpo1
      SPRING_DATASOURCE_PASSWORD: tenpo1*
    ports:
      - "8080:8080"
    networks:
      - tenpo-net

volumes:
  pgdata:

networks:
  tenpo-net:

```
2. Desde la terminal, ubícate en el directorio que contiene el archivo y ejecuta:
```bash
docker-compose up -d
```
   - `-d` ejecuta los contenedores en segundo plano (detached mode).

   - Si es la primera vez que ejecutas el comando, Docker descargará la imagen de la API desde Docker Hub y creará los contenedores.
3. Verifica que los contenedores estén corriendo:
```bash
docker ps
```
   - Deberías ver dos contenedores: `tenpo_db_postgres` y `tenpo-app`.
4. Accede a la API en tu navegador o herramienta de pruebas (Postman, Insomnia, etc.) en la siguiente URL:
5. ```http
http://localhost:8080/swagger-ui/index.html   ```
   - Deberías ver la documentación de la API.
6. Para detener los contenedores, ejecuta:
```bash
docker-compose down
```
   - Esto detendrá y eliminará los contenedores, pero no eliminará los volúmenes de datos.
   - Si deseas eliminar también los volúmenes, puedes usar:
   - ```bash
        docker-compose down -v
     ```
    - Esto eliminará los contenedores y los volúmenes asociados.
---
## 🛠️ Notas
- Asegúrate de que el puerto `8080` no esté en uso por otro servicio en tu máquina local.
- Si necesitas acceder a la base de datos PostgreSQL, puedes usar un cliente como DBeaver o pgAdmin y conectarte a `localhost:5432` con las credenciales definidas en el archivo `docker-compose.yml`.
- Si deseas acceder a la base de datos desde otro contenedor, usa el nombre del servicio `db` como host.
- Si necesitas más información sobre la API, consulta la documentación en el endpoint `/swagger-ui/index.html`.
- Si deseas personalizar la configuración de la base de datos o la API, puedes modificar las variables de entorno en el archivo `docker-compose.yml`.
- Si deseas ver los logs de la API, puedes usar el siguiente comando:
```bash
docker logs -f tenpo-app
```
- Si deseas acceder a la consola de PostgreSQL dentro del contenedor, puedes usar el siguiente comando:
```bash
docker exec -it tenpo_db_postgres psql -U tenpo1 -d tenpoDB
```
- Si deseas acceder a la consola de la API dentro del contenedor, puedes usar el siguiente comando:
```bash 
docker exec -it tenpo-app /bin/sh
```
- Si deseas eliminar todos los contenedores y volúmenes de Docker, puedes usar el siguiente comando:
```bash
docker system prune -a --volumes
```
- Si deseas eliminar una imagen específica de Docker, puedes usar el siguiente comando:
```bash
docker rmi <image_id>
```
- Si deseas eliminar un contenedor específico de Docker, puedes usar el siguiente comando:
```bash
docker rm <container_id>
```
- Si deseas eliminar un volumen específico de Docker, puedes usar el siguiente comando:
```bash
docker volume rm <volume_name>
```
- Si deseas eliminar una red específica de Docker, puedes usar el siguiente comando:
```bash
docker network rm <network_name>
```
