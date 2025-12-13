package com.veljko.airline_ops.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name = "aircrafts")
public class Aircraft {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Manufacturer is required")
    @Column(nullable = false)
    private String manufacturer;

    @NotBlank(message = "Aircraft type is required")
    @Column(nullable = false)
    private String type;

    @NotBlank(message = "Registration is required")
    @Column(nullable = false, unique = true)
    private String registration;

    @NotNull(message = "Seating capacity is required")
    @Positive(message = "Seating capacity must be positive")
    @Column(nullable = false)
    private Integer seatingCapacity;

    @NotNull(message = "Max takeoff weight is required")
    @Positive(message = "Max takeoff weight must be positive")
    @Column(nullable = false)
    private Integer maxTakeoffWeightKg;

    public Aircraft() {
    }

    //getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
