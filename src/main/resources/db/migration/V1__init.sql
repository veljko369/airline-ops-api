CREATE TABLE airports (
    id BIGSERIAL PRIMARY KEY,
    code VARCHAR(10) NOT NULL UNIQUE,
    name VARCHAR(255) NOT NULL,
    city VARCHAR(255) NOT NULL,
    country VARCHAR(255) NOT NULL
);

CREATE TABLE aircrafts (
    id BIGSERIAL PRIMARY KEY,
    manufacturer VARCHAR(255),
    type VARCHAR(50),
    registration VARCHAR(50) NOT NULL UNIQUE,
    seating_capacity INTEGER,
    max_takeoff_weight_kg INTEGER
);

CREATE TABLE flights (
    id BIGSERIAL PRIMARY KEY,
    flight_number VARCHAR(20) NOT NULL,
    scheduled_departure TIMESTAMP NOT NULL,
    scheduled_arrival TIMESTAMP NOT NULL,
    status VARCHAR(30),
    gate VARCHAR(20),

    aircraft_id BIGINT REFERENCES aircrafts(id),
    origin_airport_id BIGINT REFERENCES airports(id),
    destination_airport_id BIGINT REFERENCES airports(id),

    planned_payload_kg INTEGER,
    actual_payload_kg INTEGER,
    fuel_kg INTEGER
);

CREATE INDEX idx_flights_status ON flights(status);
CREATE INDEX idx_flights_aircraft_id ON flights(aircraft_id);
CREATE INDEX idx_flights_origin_airport_id ON flights(origin_airport_id);
CREATE INDEX idx_flights_destination_airport_id ON flights(destination_airport_id);