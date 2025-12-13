package com.veljko.airline_ops.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class CreateAircraftRequest {

    @NotBlank(message = "Manufacturer is required")
    private String manufacturer;

    @NotBlank(message = "Aircraft type is required")
    private String type;

    @NotBlank(message = "Registration is required")
    private String registration;

    @NotNull(message = "Seating capacity is required")
    @Positive(message = "Seating capacity must be positive")
    private Integer seatingCapacity;

    @NotNull(message = "Max takeoff weight is required")
    @Positive(message = "Max takeoff weight must be positive")
    private Integer maxTakeoffWeightKg;

    //getters and setters
    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    public Integer getSeatingCapacity() {
        return seatingCapacity;
    }

    public void setSeatingCapacity(Integer seatingCapacity) {
        this.seatingCapacity = seatingCapacity;
    }

    public Integer getMaxTakeoffWeightKg() {
        return maxTakeoffWeightKg;
    }

    public void setMaxTakeoffWeightKg(Integer maxTakeoffWeightKg) {
        this.maxTakeoffWeightKg = maxTakeoffWeightKg;
    }
}
