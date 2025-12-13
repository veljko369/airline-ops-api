package com.veljko.airline_ops.controller;

import com.veljko.airline_ops.dto.CreateFlightRequest;
import com.veljko.airline_ops.dto.UpdateFlightStatusRequest;
import com.veljko.airline_ops.dto.UpdateGateRequest;
import com.veljko.airline_ops.dto.UpdateWeightBalanceRequest;
import com.veljko.airline_ops.model.Flight;
import com.veljko.airline_ops.model.FlightStatus;
import com.veljko.airline_ops.service.FlightService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
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

    @GetMapping("/{id}")
    public Flight getFlightById(@PathVariable Long id) {
        return flightService.getFlightById(id);
    }

    @PostMapping
    public Flight createFlight(@Valid @RequestBody CreateFlightRequest request) {
        return flightService.createFlight(request);
    }

    @PatchMapping("/{id}/status")
    public Flight updateFlightStatus(@PathVariable Long id,
                                     @Valid @RequestBody UpdateFlightStatusRequest request) {
        return flightService.updateStatus(id, request.getStatus());
    }

    @PatchMapping("/{id}/gate")
    public Flight updateFlightGate(@PathVariable Long id,
                                   @Valid @RequestBody UpdateGateRequest request) {
        return flightService.updateGate(id, request.getGate());
    }

    @PatchMapping("/{id}/weight-balance")
    public Flight updateWeightBalance(@PathVariable Long id,
                                      @Valid @RequestBody UpdateWeightBalanceRequest request) {
        return flightService.updateWeightBalance(
                id,
                request.getPlannedPayloadKg(),
                request.getActualPayloadKg(),
                request.getFuelKg()
        );
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

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteFlight(@PathVariable Long id) {
        flightService.deleteFlight(id);
    }
}
