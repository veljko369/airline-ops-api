package com.veljko.airline_ops.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.time.LocalDateTime;

@Entity
@Table(name = "flights")
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Flight number is required")
    @Column(nullable = false)
    private String flightNumber;

    @NotNull(message = "Scheduled departure is required")
    @Column(nullable = false)
    private LocalDateTime scheduledDeparture;

    @NotNull(message = "Scheduled arrival is required")
    @Column(nullable = false)
    private LocalDateTime scheduledArrival;

    @NotNull(message = "Status is required")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FlightStatus status;

    @Column(length = 10)
    private String gate;

    @NotNull(message = "Aircraft is required")
    @ManyToOne
    @JoinColumn(name = "aircraft_id", nullable = false)
    private Aircraft aircraft;

    @NotNull(message = "Origin airport is required")
    @ManyToOne
    @JoinColumn(name = "origin_airport_id", nullable = false)
    private Airport originAirport;

    @NotNull(message = "Destination airport is required")
    @ManyToOne
    @JoinColumn(name = "destination_airport_id", nullable = false)
    private Airport destinationAirport;

    //weight and balance
    @PositiveOrZero(message = "Planned payload must be >= 0")
    private Integer plannedPayloadKg;

    @PositiveOrZero(message = "Actual payload must be >= 0")
    private Integer actualPayloadKg;

    @PositiveOrZero(message = "Fuel must be >= 0")
    private Integer fuelKg;

    public Flight() {
    }

    //getters and setters
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

    public Aircraft getAircraft() {
        return aircraft;
    }

    public void setAircraft(Aircraft aircraft) {
        this.aircraft = aircraft;
    }

    public Airport getOriginAirport() {
        return originAirport;
    }

    public void setOriginAirport(Airport originAirport) {
        this.originAirport = originAirport;
    }

    public Airport getDestinationAirport() {
        return destinationAirport;
    }

    public void setDestinationAirport(Airport destinationAirport) {
        this.destinationAirport = destinationAirport;
    }
}
