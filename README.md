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

### Create a product POST

```json
{
    "name": "SoundBar",
    "description": "Sounbar",
    "price": 15000,
    "stock": 50
}
```
