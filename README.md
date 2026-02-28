# Airline Operations API

Backend REST API that models a simplified airline operations system.

This project was built as part of my transition from air traffic engineering to backend development using Java and Spring Boot.

---

## Tech Stack

* Java 21
* Spring Boot
* Spring Web (REST)
* Spring Data JPA (Hibernate)
* PostgreSQL + Flyway
* Spring Security (HTTP Basic + role-based authorization)
* JUnit 5 + Mockito
* Swagger / OpenAPI
* Docker (multi-stage build)
* GitHub Actions (CI / CD)

---

## Architecture

Layered architecture:

Controller → Service → Repository → Database

* Controllers handle HTTP requests
* Services contain business logic
* Repositories manage data access

---

## Core Features

### Flights

* CRUD operations
* Aircraft assignment
* Origin and destination airports
* Partial updates (PATCH):

  * status
  * gate
  * weight & balance

### Aircraft

* CRUD operations
* Registration uniqueness validation

### Airports

* CRUD operations
* Code uniqueness validation

### Filtering

Flights can be filtered by:

* id
* status
* origin airport code
* destination airport code
* aircraft ID


---

## Security

Implemented using Spring Security.

Authentication:

* HTTP Basic authentication
* In-memory demo users

Authorization:

* USER → read-only access (GET)
* ADMIN → full access (POST, PATCH, DELETE)

Demo credentials:

* user / user123
* admin / admin123

---

## Database

* PostgreSQL used as primary database
* Flyway manages versioned schema migrations
* Hibernate configured with `ddl-auto=validate`
* Schema validation enforced at startup

---

## Testing

### Unit Tests

* Service layer tested using JUnit 5 and Mockito
* Repository layer mocked
* Business logic verified independently

### Integration Tests

* Controller-level tests
* Full Spring context loading
* HTTP request simulation using MockMvc

Tests are executed automatically via GitHub Actions.

---

## Docker

* Multi-stage Dockerfile (build + runtime separation)
* Docker Compose configuration including PostgreSQL
* Persistent volume for database storage

The application can be started locally or fully containerized.

---

## CI / CD

### Continuous Integration

On every push to `main`:

* Maven tests are executed
* PostgreSQL service is started
* Build is validated

### Continuous Delivery (light)

If tests pass:

* Docker image is built
* Image is pushed to GitHub Container Registry (GHCR)

---

## API Documentation

Swagger UI available at:

[http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

---

## Running the Project

### Run locally (requires PostgreSQL)

mvn spring-boot:run

### Run with Docker

docker compose up --build

---

## Author

## Veljko Mihajlović

Java Backend Developer

Air Traffic Engineer transitioning into IT

---
