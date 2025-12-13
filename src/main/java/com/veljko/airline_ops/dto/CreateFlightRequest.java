package com.veljko.airline_ops.dto;

import com.veljko.airline_ops.model.FlightStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public class CreateFlightRequest {

    @NotBlank(message = "Flight number is required")
    private String flightNumber;

    @NotNull(message = "Origin airport ID is required")
    private Long originAirportId;

    @NotNull(message = "Destination airport ID is required")
    private Long destinationAirportId;

    @NotNull(message = "Scheduled departure time is required")
    private LocalDateTime scheduledDeparture;

    @NotNull(message = "Scheduled arrival time is required")
    private LocalDateTime scheduledArrival;

    @NotNull(message = "Aircraft ID is required")
    private Long aircraftId;

    @PositiveOrZero(message = "Planned payload must be >= 0")
    private Integer plannedPayloadKg;

    @PositiveOrZero(message = "Actual payload must be >= 0")
    private Integer actualPayloadKg;

    @PositiveOrZero(message = "Fuel amount must be >= 0")
    private Integer fuelKg;

    //default in service
    private FlightStatus status;

    @Size(max = 10, message = "Gate must be at most 10 characters")
    private String gate;

    //getters and setters
    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public Long getOriginAirportId() {
        return originAirportId;
    }

    public void setOriginAirportId(Long originAirportId) {
        this.originAirportId = originAirportId;
    }

    public Long getDestinationAirportId() {
        return destinationAirportId;
    }

    public void setDestinationAirportId(Long destinationAirportId) {
        this.destinationAirportId = destinationAirportId;
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

    public Long getAircraftId() {
        return aircraftId;
    }

    public void setAircraftId(Long aircraftId) {
        this.aircraftId = aircraftId;
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
}
