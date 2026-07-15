# Inventory Platform

A production-style **Spring Boot Microservices** application demonstrating enterprise backend architecture, secure authentication, distributed communication, and event-driven processing.

## Tech Stack

* Java 17
* Spring Boot
* Spring Cloud
* Spring Security (JWT)
* Spring Cloud Gateway
* Eureka Server
* Spring Cloud Config
* Spring Data JPA
* MySQL
* Apache Kafka
* OpenFeign
* Swagger / OpenAPI
* Maven

## Architecture

```
                 Client
                    │
                    ▼
              API Gateway
                    │
      ┌─────────────┼─────────────┐
      ▼             ▼             ▼
 User Service   Product Service  Order Service
      │             ▲             │
      │             │             │
      └─────────────┴─────────────┘
             Kafka Event Bus
```

## Implemented Features

* JWT Authentication & Authorization
* Centralized Configuration (Spring Cloud Config)
* Service Discovery (Eureka)
* API Gateway Routing
* RESTful APIs
* OpenFeign Inter-Service Communication
* Kafka Event-Driven Architecture
* Saga-based Order & Inventory Workflow
* Inventory Reservation using Kafka Events
* Global Exception Handling
* DTO Mapping
* Swagger/OpenAPI Documentation
* Layered Architecture
* Maven Multi-Module Project

## Microservices

* Config Server
* Eureka Server
* API Gateway
* User Service
* Product Service
* Order Service
* Common Library

## Planned Enhancements

* Notification Service (Email/SMS)
* Payment Service
* Resilience4j (Circuit Breaker, Retry)
* Redis Cache
* Docker & Docker Compose
* Kubernetes
* GitHub Actions (CI/CD)
* AWS Deployment (EC2, RDS, S3)

## Running the Project

1. Start Config Server
2. Start Eureka Server
3. Start Kafka
4. Start User Service
5. Start Product Service
6. Start Order Service
7. Access services through API Gateway
