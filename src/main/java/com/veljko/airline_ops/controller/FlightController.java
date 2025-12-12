package com.veljko.airline_ops.controller;

import com.veljko.airline_ops.dto.CreateFlightRequest;
import com.veljko.airline_ops.dto.UpdateFlightStatusRequest;
import com.veljko.airline_ops.dto.UpdateGateRequest;
import com.veljko.airline_ops.dto.UpdateWeightBalanceRequest;
import com.veljko.airline_ops.model.Flight;
import com.veljko.airline_ops.model.FlightStatus;
import com.veljko.airline_ops.service.FlightService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/flights")
public class FlightController {

    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping
    public List<Flight> getAllFlights() {
        return flightService.getAllFlights();
    }

    @PostMapping
    public Flight createFlight(@Valid @RequestBody CreateFlightRequest request) {
        return flightService.createFlight(request);
    }

    @PostMapping("/{id}/status")
    public Flight updateFlightStatus(@PathVariable Long id,
                                     @Valid @RequestBody UpdateFlightStatusRequest request) {
        FlightStatus newStatus = request.getStatus();
        return flightService.updateStatus(id, newStatus);
    }

    @PostMapping("/{id}/gate")
    public Flight updateFlightGate(@PathVariable Long id,
                                   @Valid @RequestBody UpdateGateRequest request) {
        String newGate = request.getGate();
        return flightService.updateGate(id, newGate);
    }

    @PostMapping("/{id}/weight-balance")
    public Flight updateWeightBalance(@PathVariable Long id,
                                      @Valid @RequestBody UpdateWeightBalanceRequest request) {
        Integer newPlannedPayloadKg = request.getPlannedPayloadKg();
        Integer newActualPayloadKg = request.getActualPayloadKg();
        Integer newFuelKg = request.getFuelKg();

        return flightService.updateWeightBalance(id, newPlannedPayloadKg, newActualPayloadKg, newFuelKg);


    }

    @GetMapping("/status/{status}")
    public List<Flight> getFlightsByStatus(@PathVariable FlightStatus status) {
        return flightService.getFlightsByStatus(status);
    }

    @GetMapping("/origin/{origin}")
    public List<Flight> getFlightsByOrigin(@PathVariable String origin) {
        return flightService.getFlightsByOrigin(origin);
    }

    @GetMapping("/destination/{destination}")
    public List<Flight> getFlightsByDestination(@PathVariable String destination) {
        return flightService.getFlightsByDestination(destination);
    }

    @GetMapping("/aircraft/{aircraftId}")
    public List<Flight> getFlightsByAircraft(@PathVariable Long aircraftId) {
        return flightService.getFlightsByAircraft(aircraftId);
    }


}
