
# Airline Operations API

A backend REST API that models a simplified airline operations system, inspired by real OCC/dispatch workflows.  
Built with **Java 21**, **Spring Boot**, **Spring Data JPA**, and **H2**.

The goal of this project is to demonstrate clean backend architecture, CRUD operations, DTO patterns, entity relationships, and domain-driven modeling using realistic aviation concepts.

---

## Features

- CRUD for **Flights**, **Aircraft**, and **Airports**
- **Assigning Aircraft to Flights** (Many-to-One)
- **Flight status updates** (SCHEDULED, BOARDING, DELAYED, etc.)
- **Gate assignment updates**
- **Weight & Balance updates** (planned payload, actual payload, fuel)
- **Filtering flights** by:
  - status  
  - origin  
  - destination  
  - aircraft  
- Search flights by **aircraftId**
- Clean layered architecture:
  - Controller → Service → Repository
  - DTOs for request models

---

## Why Aviation Domain?

My background is in air-traffic engineering, so instead of building a generic CRUD demo,  
I wanted to model something close to real airline operations:

- flight scheduling  
- gate management  
- aircraft assignment  
- payload / fuel updates  
- airport catalog  
- dispatch/OCC workflows  

This combines my previous aviation knowledge with modern backend development using Java/Spring Boot.

---

## Tech Stack

- **Java 21**
- **Spring Boot 3.5.7**
- **Spring Web (REST)**
- **Spring Data JPA**
- **H2 in-memory database**
- **Maven**
- (Swagger planned in next version)

---

## Quick Demo

### 1. Create an Aircraft
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

### 2. Create a Flight (assign aircraft via aircraftId)

`POST /api/flights`

```json
{
  "flightNumber": "JU540",
  "originAirportId": 1,
  "destinationAirportId": 2,
  "scheduledDeparture": "2025-11-20T08:30:00",
  "scheduledArrival": "2025-11-20T10:45:00",
  "status": "SCHEDULED",
  "gate": "A4",
  "aircraftId": 1,
  "plannedPayloadKg": 8000,
  "actualPayloadKg": 7800,
  "fuelKg": 5200
}
```

### 3. Get all flights

`GET /api/flights`

### 4. Filter flights by status

`GET /api/flights/status/DELAYED`

### 5. Find flights using a specific aircraft

`GET /api/flights/aircraft/1`

---
## Swagger / OpenAPI

Interactive API documentation is available via Swagger UI:

```
http://localhost:8080/swagger-ui/index.html
```

Swagger exposes all endpoints, request models, validation rules, and allows testing the API directly from the browser.

---
## API Structure

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

## Planned Next Steps

* Add basic CI pipeline (GitHub Actions)
* Optional: Dockerfile

---

## Running the Project

### 1. Clone the repo

```
git clone https://github.com/<your-username>/airline-ops-api.git
```

### 2. Run with Maven

```
mvn spring-boot:run
```

App starts on:
`http://localhost:8080`

---

## Contact

Created as part of my transition from air-traffic engineering to Java backend development.
Feel free to open issues or send suggestions.

---

## Author
Veljko Mihajlović  
Java Backend Developer (in progress)  
Air Traffic Engineer transitioning into IT

---

