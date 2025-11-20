package com.veljko.airline_ops.service;

import com.veljko.airline_ops.model.Flight;
import com.veljko.airline_ops.model.FlightStatus;
import com.veljko.airline_ops.repository.FlightRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class FlightService {

    private final FlightRepository flightRepository;

    public FlightService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    public Flight createFlight(Flight flight) {
        if (flight.getStatus() == null) {
            flight.setStatus(FlightStatus.SCHEDULED);
        }
        return flightRepository.save(flight);
    }

    public Flight getFlightById(Long id) {
        var optionalFlight = flightRepository.findById(id);

        if (optionalFlight.isEmpty()) {
            throw new ResponseStatusException(
                    NOT_FOUND,
                    "Flight with id " + id + " not found"
            );
        }

        return optionalFlight.get();
    }

    public Flight updateStatus(Long id, FlightStatus newStatus) {
        Flight flight = getFlightById(id);
        flight.setStatus(newStatus);
        return flightRepository.save(flight);
    }

    public Flight updateGate(Long id, String newGate) {

        Flight flight = getFlightById(id);

        if (newGate == null || newGate.isBlank()) {
            throw new ResponseStatusException(BAD_REQUEST, "Gate must not be empty");
        }

        flight.setGate(newGate.trim());
        return flightRepository.save(flight);
    }


    public Flight updateWeightBalance(Long id, Integer plannedPayloadKg, Integer actualPayloadKg, Integer fuelKg) {

        Flight flight = getFlightById(id);

        boolean invalidPlanned = plannedPayloadKg != null && plannedPayloadKg < 0;
        boolean invalidActual = actualPayloadKg != null && actualPayloadKg < 0;
        boolean invalidFuel = fuelKg != null && fuelKg < 0;

        if (invalidPlanned || invalidActual || invalidFuel) {
            throw new ResponseStatusException(
                    BAD_REQUEST,
                    "Payload and fuel values must not be negative"
            );
        }

        if (plannedPayloadKg != null) {
            flight.setPlannedPayloadKg(plannedPayloadKg);
        }
        if (actualPayloadKg != null) {
            flight.setActualPayloadKg(actualPayloadKg);
        }
        if (fuelKg != null) {
            flight.setFuelKg(fuelKg);
        }

        return flightRepository.save(flight);
    }


}
