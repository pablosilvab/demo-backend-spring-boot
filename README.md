# Demo Spring Boot

El objetivo de este proyecto es disponibilizar un microservicio desarrollado en Java utilizando Spring Boot.

## Ejecución 

1. Construir imagen Docker

```
docker build -t demo-backend-spring-boot .
```

2. Ejecutar imagen Docker 

```
docker run -p 8080:8080 demo-backend-spring-boot
```


## Otros

* Comando maven 

./mvnw package && java -jar target/demo-backend-spring-boot-0.0.1.jar

