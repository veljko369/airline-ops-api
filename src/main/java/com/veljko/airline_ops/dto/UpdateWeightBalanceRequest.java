package com.veljko.airline_ops.dto;

import jakarta.validation.constraints.PositiveOrZero;

public class UpdateWeightBalanceRequest {

    @PositiveOrZero(message = "Planned payload must be >= 0")
    private Integer plannedPayloadKg;

    @PositiveOrZero(message = "Actual payload must be >= 0")
    private Integer actualPayloadKg;

    @PositiveOrZero(message = "Fuel must be >= 0")
    private Integer fuelKg;

    public UpdateWeightBalanceRequest(){
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
