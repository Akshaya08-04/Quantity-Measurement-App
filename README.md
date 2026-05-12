# Quantity Measurement Application

## Overview

Quantity Measurement Application is a Spring Boot–based backend project developed to perform quantity conversions, comparisons, arithmetic operations, authentication, and history tracking.

The project evolved from a Monolithic Architecture (UC1–UC18) into a Microservices Architecture (UC19).

---

# Technologies Used

* Java 17
* Spring Boot
* Spring Security
* Spring Data JPA
* Hibernate
* Maven
* JWT Authentication
* Google OAuth2 Authentication
* Eureka Server
* API Gateway
* OpenFeign
* H2 Database
* Swagger / OpenAPI
* Git & GitHub

---

# Features

* Quantity comparison
* Unit conversion
* Arithmetic operations
* Operation history tracking
* JWT Authentication
* Google Authentication
* REST APIs
* Microservices Architecture
* Service Discovery using Eureka
* API Gateway Routing

---

# Microservices

```text
Quantity-Measurement-App
│
├── api-gateway
├── eureka-server
├── measurement-service
└── user-service
```

---

# API Endpoints

## Authentication

```http
POST /auth/register
POST /auth/login
```

## Quantity Operations

```http
POST /api/v1/quantities/operate
GET /api/v1/quantities/history
GET /api/v1/quantities/history/{operationType}
GET /api/v1/quantities/count/{operationType}
```

---

# Swagger

```text
http://localhost:8080/swagger-ui/index.html
```

---

# Eureka Dashboard

```text
http://localhost:8761
```

---

# Conclusion

This project demonstrates backend development using Spring Boot, JWT, OAuth
