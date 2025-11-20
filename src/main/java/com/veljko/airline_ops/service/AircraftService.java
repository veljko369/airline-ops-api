package com.veljko.airline_ops.service;

import com.veljko.airline_ops.model.Aircraft;
import com.veljko.airline_ops.repository.AircraftRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class AircraftService {
    private final AircraftRepository aircraftRepository;

    public AircraftService(AircraftRepository aircraftRepository) {
        this.aircraftRepository = aircraftRepository;
    }

    public List<Aircraft> getAllAircraft() {
        return aircraftRepository.findAll();
    }

    public Aircraft createAircraft(Aircraft aircraft) {
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


}
