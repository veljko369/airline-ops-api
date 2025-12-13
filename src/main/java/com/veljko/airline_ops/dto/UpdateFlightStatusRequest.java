package com.veljko.airline_ops.dto;

import com.veljko.airline_ops.model.FlightStatus;
import jakarta.validation.constraints.NotNull;

public class UpdateFlightStatusRequest {

    @NotNull(message = "Status is required")
    private FlightStatus status;

    //getters and setters
    public FlightStatus getStatus() {
        return status;
    }

    public void setStatus(FlightStatus status) {
        this.status = status;
    }

}
