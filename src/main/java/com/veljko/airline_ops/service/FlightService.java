package com.veljko.airline_ops.service;

import com.veljko.airline_ops.dto.CreateFlightRequest;
import com.veljko.airline_ops.model.Aircraft;
import com.veljko.airline_ops.model.Flight;
import com.veljko.airline_ops.model.FlightStatus;
import com.veljko.airline_ops.repository.AircraftRepository;
import com.veljko.airline_ops.repository.FlightRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class FlightService {

    private final AircraftRepository aircraftRepository;
    private final FlightRepository flightRepository;

    public FlightService(FlightRepository flightRepository, AircraftRepository aircraftRepository) {
        this.flightRepository = flightRepository;
        this.aircraftRepository = aircraftRepository;
    }

    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }


    public Flight createFlight(CreateFlightRequest request) {

        Aircraft aircraft = aircraftRepository.findById(request.getAircraftId()).orElse(null);

        if (aircraft == null) {
            throw new ResponseStatusException(BAD_REQUEST, "Aircraft with id " + request.getAircraftId() + " not found");
        }

        Flight flight = new Flight();
        flight.setFlightNumber(request.getFlightNumber());
        flight.setOrigin(request.getOrigin());
        flight.setDestination(request.getDestination());
        flight.setScheduledDeparture(request.getScheduledDeparture());
        flight.setScheduledArrival(request.getScheduledArrival());

        if (request.getStatus() == null) {
            flight.setStatus(FlightStatus.SCHEDULED);
        } else {
            flight.setStatus(request.getStatus());
        }

        flight.setGate(request.getGate());
        flight.setAircraft(aircraft);
        flight.setPlannedPayloadKg(request.getPlannedPayloadKg());
        flight.setActualPayloadKg(request.getActualPayloadKg());
        flight.setFuelKg(request.getFuelKg());

        return flightRepository.save(flight);

    }


    public Flight getFlightById(Long id) {
        Flight flight = flightRepository.findById(id).orElse(null);

        if (flight == null) {
            throw new ResponseStatusException(
                    NOT_FOUND,
                    "Flight with id " + id + " not found"
            );
        }

        return flight;
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


    public Flight updateWeightBalance(Long id, Integer newPlannedPayloadKg, Integer newActualPayloadKg, Integer newFuelKg) {

        Flight flight = getFlightById(id);

        boolean invalidPlanned = newPlannedPayloadKg != null && newPlannedPayloadKg < 0;
        boolean invalidActual = newActualPayloadKg != null && newActualPayloadKg < 0;
        boolean invalidFuel = newFuelKg != null && newFuelKg < 0;

        if (invalidPlanned || invalidActual || invalidFuel) {
            throw new ResponseStatusException(
                    BAD_REQUEST,
                    "Payload and fuel values must not be negative"
            );
        }

        if (newPlannedPayloadKg != null) {
            flight.setPlannedPayloadKg(newPlannedPayloadKg);
        }
        if (newActualPayloadKg != null) {
            flight.setActualPayloadKg(newActualPayloadKg);
        }
        if (newFuelKg != null) {
            flight.setFuelKg(newFuelKg);
        }

        return flightRepository.save(flight);
    }


    public List<Flight> getFlightsByStatus(FlightStatus status) {
        return flightRepository.findByStatus(status);
    }

    public List<Flight> getFlightsByOrigin(String origin) {
        return flightRepository.findByOriginIgnoreCase(origin);
    }

    public List<Flight> getFlightsByDestination(String destination) {
        return flightRepository.findByDestinationIgnoreCase(destination);
    }

    public List<Flight> getFlightsByAircraft(Long id){
        return flightRepository.findByAircraftId(id);
    }

}
