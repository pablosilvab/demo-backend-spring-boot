# Demo Spring Boot

This project is an API for products management. 
 
## Build with

* Java 
* Spring Boot
 

## Features

This API provide the following endpoints:

* Get all products: GET `/api/v1/products`
* Get product by id: GET `/api/v1/products/1`
* Create a product: POST `/api/v1/products/`

## Details 

### Create and run an executable jar

```bash
mvn clean package
```

```bash
java -jar target/docker-message-server-1.0.0.jar
```

### Run with Maven

```bash
mvn spring-boot:run
```

### Run with Docker

* Build image 
```bash
docker build -t demo-backend-spring-boot .
```

* Run a container
```bash
docker run -p 8080:8080 demo-backend-spring-boot
```


### Create a product POST

```json
{
    "name": "SoundBar",
    "description": "Sounbar",
    "price": 15000,
    "stock": 50
}
```
