
# Airline Operations API

This is a backend project I am building while learning Java backend development.
The goal is to simulate a simple version of real airline operations systems (OCC, dispatch, gate coordination, etc).

The API allows basic operations on flights: creating flights, updating status, updating gate information, and updating weight & balance data.

---

## Tech Stack

* Java 17
* Spring Boot
* Spring Web
* Spring Data JPA
* H2 Database (in-memory)
* Maven

---

## How to Run

1. Install Java 17+
2. Install Maven
3. Clone the repository:

```
git clone https://github.com/veljko369/airline-ops-api.git
          
```

4. Start the application:

```
mvn spring-boot:run
```

The API will run on:
`http://localhost:8080`

---

## Endpoints

### **Health Check**

```
GET /api/health
```

---

### **Get All Flights**

```
GET /api/flights
```

---

### **Create Flight**

```
POST /api/flights
```

Example request:

```json
{
  "flightNumber": "JU540",
  "origin": "BEG",
  "destination": "LHR",
  "scheduledDeparture": "2025-11-20T08:30:00",
  "scheduledArrival": "2025-11-20T10:45:00",
  "status": "SCHEDULED",
  "gate": "A4",
  "aircraftType": "A320"
}
```

---

### **Update Flight Status**

```
POST /api/flights/{id}/status
```

Example:

```json
{
  "status": "DELAYED"
}
```

---

### **Update Gate**

```
POST /api/flights/{id}/gate
```

Example:

```json
{
  "gate": "C7"
}
```

---

### **Update Weight & Balance**

```
POST /api/flights/{id}/weight-balance
```

Example:

```json
{
  "plannedPayloadKg": 8000,
  "actualPayloadKg": 8600,
  "fuelKg": 5300
}
```

---

## About the Project

This project is part of my learning path as I transition into backend development.
I chose the aviation domain because of my background in air traffic engineering and my interest in airline operations.
The project helps me practice:

* Spring Boot
* REST API design
* Basic backend architecture
* Working with a database using JPA
* Structuring code (Controller → Service → Repository)

---

## Planned Improvements

* Filter flights by status, origin, destination
* Add Aircraft entity
* Add flight history
* Add Swagger/OpenAPI documentation
* Add authentication (JWT)

---

## Author

**Veljko Mihajlović**  
Java Backend Developer (in progress)  
Air Traffic Engineer transitioning into IT

---
