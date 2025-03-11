# Demo Spring Boot API

This project is a **REST API** for managing products, built using **Spring Boot** and **Java 17**.

## 🚀 Technologies Used

- **Java 21**
- **Spring Boot 3.4.3**
- **Spring Data JPA** (for database interaction)
- **Spring Validation** (for request validation)
- **MapStruct 1.6.0** (for object mapping)
- **Lombok** (to reduce boilerplate code)
- **Swagger** (for API documentation)
- **Docker** (for containerization)

## 📌 Features

This API provides the following endpoints:

| Method | Endpoint                     | Description                  |
|--------|------------------------------|------------------------------|
| GET    | `/api/v1/products`           | Retrieve all products        |
| GET    | `/api/v1/products/{id}`      | Retrieve a product by ID     |
| POST   | `/api/v1/products`           | Create a new product        |
| PUT    | `/api/v1/products/{id}`      | Update an existing product  |
| DELETE | `/api/v1/products/{id}`      | Delete a product            |

## 🛠️ Setup and Usage

### 1️⃣ Clone the repository
```bash
git clone https://github.com/your-username/demo-backend-spring-boot.git
cd demo-backend-spring-boot
```

### 2️⃣ Run with Maven
```bash
mvn spring-boot:run
```

### 3️⃣ Run with Docker

#### Build the Docker image
```bash
docker build -t demo-backend-spring-boot .
```

#### Run Docker services
```bash
docker-compose up -d
```

#### Push Docker image
```bash
docker tag demo-backend-spring-boot pablon27/demo-backend-spring-boot:1.0.0
docker push pablon27/demo-backend-spring-boot:1.0.0
```


### 4️⃣ API Usage Example
#### Create a Product (POST `/api/v1/products`)
```json
{
    "name": "SoundBar",
    "description": "High-quality sound system",
    "price": 15000,
    "stock": 50
}
```

#### Get a Product by ID (GET `/api/v1/products/1`)
```json
{
    "id": 1,
    "name": "SoundBar",
    "description": "High-quality sound system",
    "price": 15000,
    "stock": 50
}
```

## 📄 API Documentation
You can explore and test the API using **Swagger UI**:
```
http://localhost:8080/swagger-ui/index.html
```

## ✅ License
This project is open-source and available under the [MIT License](LICENSE).