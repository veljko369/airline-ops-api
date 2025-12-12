package com.veljko.airline_ops.dto;

import jakarta.validation.constraints.NotBlank;

public class UpdateGateRequest {
    @NotBlank(message = "Gate cannot be empty")
    private String gate;

    public UpdateGateRequest() {
    }

    public String getGate() {
        return gate;
    }

    public void setGate(String gate) {
        this.gate = gate;
    }
}
