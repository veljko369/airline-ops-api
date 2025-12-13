# Airline Operations API

A backend REST API that models a simplified airline operations system, inspired by real airline OCC / dispatch workflows.

Built with **Java 21**, **Spring Boot**, **Spring Data JPA**, and **H2**.

The purpose of this project is to demonstrate clean backend architecture, RESTful API design, DTO usage, validation, entity relationships, and domain-driven modeling using realistic aviation concepts.

---

## Features

### Core Functionality
- CRUD operations for **Flights**, **Aircraft**, and **Airports**
- Assigning **Aircraft to Flights** (Many-to-One)
- Origin and destination airports modeled as entities
- Flight lifecycle management using status enum

### Update Operations (PATCH)
- Update flight status
- Update gate assignment
- Update weight & balance data:
  - planned payload
  - actual payload
  - fuel amount

### Filtering & Search
- Filter flights by:
  - status
  - origin airport code
  - destination airport code
  - aircraft
- Retrieve flights assigned to a specific aircraft

### Validation & Error Handling
- Request validation using `@Valid`, `@NotNull`, `@NotBlank`, `@PositiveOrZero`
- Consistent error responses with HTTP status codes
- Safe delete operations (e.g. aircraft cannot be deleted if assigned to flights)

### Architecture
- Layered architecture:
  - Controller → Service → Repository
- Clear separation of concerns
- DTOs used for request payloads
- JPA entity relationships

---

## Why Aviation Domain?

My background is in air traffic engineering, so instead of building a generic CRUD demo, this project models real-world airline operations such as:

- flight scheduling
- gate management
- aircraft assignment
- payload and fuel updates

This project combines domain knowledge from aviation with modern backend development using Java and Spring Boot.

---

## Tech Stack

- **Java 21**
- **Spring Boot 3.5.7**
- **Spring Web (REST)**
- **Spring Data JPA**
- **H2 in-memory database**
- **Bean Validation (Jakarta Validation)**
- **Swagger / OpenAPI (springdoc)**
- **Maven**

---

## Swagger / OpenAPI

Interactive API documentation is available via Swagger UI:

```

[http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

````

Swagger exposes all endpoints, request models, validation rules, and allows testing the API directly from the browser.

---

## API Overview

### Example: Create Aircraft
`POST /api/aircraft`

```json
{
  "registration": "YU-APH",
  "type": "A320",
  "manufacturer": "Airbus",
  "seatingCapacity": 180,
  "maxTakeoffWeightKg": 73500
}
````

### Example: Create Flight

`POST /api/flights`

```json
{
  "flightNumber": "JU540",
  "originAirportId": 1,
  "destinationAirportId": 2,
  "scheduledDeparture": "2025-11-20T08:30:00",
  "scheduledArrival": "2025-11-20T10:45:00",
  "gate": "A4",
  "aircraftId": 1,
  "plannedPayloadKg": 8000,
  "actualPayloadKg": 7800,
  "fuelKg": 5200
}
```

### Other Endpoints

* `GET /api/flights`
* `GET /api/flights/{id}`
* `PATCH /api/flights/{id}/status`
* `PATCH /api/flights/{id}/gate`
* `PATCH /api/flights/{id}/weight-balance`
* `DELETE /api/flights/{id}`
* `DELETE /api/aircraft/{id}`

---

## Project Structure

```
src/main/java/com/veljko/airline_ops/
│
├── controller/
│   ├── FlightController.java
│   ├── AircraftController.java
│   └── AirportController.java
│
├── dto/
│   ├── CreateFlightRequest.java
│   ├── CreateAircraftRequest.java
│   ├── CreateAirportRequest.java
│   ├── UpdateFlightStatusRequest.java
│   ├── UpdateGateRequest.java
│   └── UpdateWeightBalanceRequest.java
│
├── model/
│   ├── Flight.java
│   ├── FlightStatus.java
│   ├── Aircraft.java
│   └── Airport.java
│
├── repository/
├── service/
└── AirlineOpsApplication.java
```

---

## Running the Project

### 1. Clone the repository

```
git clone https://github.com/<your-username>/airline-ops-api.git
```

### 2. Run with Maven

```
mvn spring-boot:run
```

Application starts at:

```
http://localhost:8080
```

---

## Roadmap

* Add tests 
* Add SQL persistence
* Add Docker

---

## Author

Veljko Mihajlović  
Java Backend Developer (in progress)  
Air Traffic Engineer transitioning into IT
---
