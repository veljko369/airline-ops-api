package com.veljko.airline_ops.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UpdateGateRequest {

    @NotBlank(message = "Gate cannot be empty")
    @Size(max =10, message = "Gate must be at most 10 characters")
    private String gate;

    //getters and setters
    public String getGate() {
        return gate;
    }

    public void setGate(String gate) {
        this.gate = gate;
    }
}
