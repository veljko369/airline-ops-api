package com.veljko.airline_ops.model;

import jakarta.persistence.*;

@Entity
@Table(name = "airtcaft")
public class Aircraft {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String manufacturer;
    private String type;
    private String registration;
    private Integer seatingCapacity;
    private Integer maxTakeoffWeightKg;

    public Aircraft() {

    }

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
