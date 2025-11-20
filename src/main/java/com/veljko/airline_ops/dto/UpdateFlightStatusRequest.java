package com.veljko.airline_ops.dto;

import com.veljko.airline_ops.model.FlightStatus;

public class UpdateFlightStatusRequest {

    private FlightStatus status;

    public UpdateFlightStatusRequest() {
    }

    public FlightStatus getStatus() {
        return status;
    }

    public void setStatus(FlightStatus status) {
        this.status = status;
    }

}
