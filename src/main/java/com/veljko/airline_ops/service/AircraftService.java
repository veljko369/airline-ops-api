package com.veljko.airline_ops.service;

import com.veljko.airline_ops.dto.CreateAircraftRequest;
import com.veljko.airline_ops.model.Aircraft;
import com.veljko.airline_ops.repository.AircraftRepository;
import com.veljko.airline_ops.repository.FlightRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class AircraftService {

    private final AircraftRepository aircraftRepository;
    private final FlightRepository flightRepository;

    public AircraftService(AircraftRepository aircraftRepository, FlightRepository flightRepository) {
        this.aircraftRepository = aircraftRepository;
        this.flightRepository = flightRepository;
    }

    public List<Aircraft> getAllAircraft() {
        return aircraftRepository.findAll();
    }

    public Aircraft createAircraft(CreateAircraftRequest request) {
        String normalizedRegistration = request.getRegistration().trim().toUpperCase();
        String normalizedType = request.getType().trim().toUpperCase();
        String normalizedManufacturer = request.getManufacturer().trim();

        if (aircraftRepository.existsByRegistration(normalizedRegistration)) {
            throw new ResponseStatusException(
                    BAD_REQUEST,
                    "Aircraft with registration " + normalizedRegistration + " alredy exists"
            );
        }

        Aircraft aircraft = new Aircraft();
        aircraft.setRegistration(normalizedRegistration);
        aircraft.setType(normalizedType);
        aircraft.setManufacturer(normalizedManufacturer);
        aircraft.setSeatingCapacity(request.getSeatingCapacity());
        aircraft.setMaxTakeoffWeightKg(request.getMaxTakeoffWeightKg());

        return aircraftRepository.save(aircraft);
    }

    public Aircraft getAircraftById(Long id) {
        Aircraft aircraft = aircraftRepository.findById(id).orElse(null);

        if (aircraft == null) {
            throw new ResponseStatusException(
                    NOT_FOUND,
                    "Aircraft with id " + id + " not found"
            );
        }

        return aircraft;
    }

    public void deleteAircraft(Long id) {
        Aircraft aircraft = getAircraftById(id);

        if (flightRepository.existsByAircraft_Id(id)) {
            throw new ResponseStatusException(
                    BAD_REQUEST,
                    "Cannot delete aircraft " + aircraft.getRegistration() + " because it is assigned to one or more flights"
            );
        }

        aircraftRepository.delete(aircraft);
    }
}
