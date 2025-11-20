package com.veljko.airline_ops.dto;

public class UpdateWeightBalanceRequest {

    private Integer plannedPayloadKg;
    private Integer actualPayloadKg;
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
