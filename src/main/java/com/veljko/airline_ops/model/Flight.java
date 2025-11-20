package com.veljko.airline_ops.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "flights")
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String flightNumber;
    private String origin;
    private String destination;
    private LocalDateTime scheduledDeparture;
    private LocalDateTime scheduledArrival;
    @Enumerated(EnumType.STRING)
    private FlightStatus status;
    private String gate;
    private String aircraftType;
    //weight and balance
    private Integer plannedPayloadKg;
    private Integer actualPayloadKg;
    private Integer fuelKg;

    public Flight() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public LocalDateTime getScheduledDeparture() {
        return scheduledDeparture;
    }

    public void setScheduledDeparture(LocalDateTime scheduledDeparture) {
        this.scheduledDeparture = scheduledDeparture;
    }

    public LocalDateTime getScheduledArrival() {
        return scheduledArrival;
    }

    public void setScheduledArrival(LocalDateTime scheduledArrival) {
        this.scheduledArrival = scheduledArrival;
    }

    public FlightStatus getStatus() {
        return status;
    }

    public void setStatus(FlightStatus status) {
        this.status = status;
    }

    public String getGate() {
        return gate;
    }

    public void setGate(String gate) {
        this.gate = gate;
    }

    public String getAircraftType() {
        return aircraftType;
    }

    public void setAircraftType(String aircraftType) {
        this.aircraftType = aircraftType;
    }

    public Integer getPlannedPayloadKg() {
        return plannedPayloadKg;
    }

    public void setPlannedPayloadKg(Integer plannedPayloadKg) {
        this.plannedPayloadKg = plannedPayloadKg;
    }

    public Integer getActualPayloadKg() {
        return actualPayloadKg;
    }

    public void setActualPayloadKg(Integer actualPayloadKg) {
        this.actualPayloadKg = actualPayloadKg;
    }

    public Integer getFuelKg() {
        return fuelKg;
    }

    public void setFuelKg(Integer fuelKg) {
        this.fuelKg = fuelKg;
    }
}
