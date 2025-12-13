package com.veljko.airline_ops.service;

import com.veljko.airline_ops.dto.CreateAirportRequest;
import com.veljko.airline_ops.model.Airport;
import com.veljko.airline_ops.repository.AirportRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class AirportService {

    private final AirportRepository airportRepository;

    public AirportService(AirportRepository airportRepository) {
        this.airportRepository = airportRepository;
    }

    public List<Airport> getAllAirports() {
        return airportRepository.findAll();
    }

    public Airport createAirport(CreateAirportRequest request) {
        String normalizedCode = request.getCode().trim().toUpperCase();

        if(airportRepository.existsByCode(normalizedCode)){
            throw new ResponseStatusException(
                    BAD_REQUEST,
                    "Airport with code " + normalizedCode + " alredy exists"
            );
        }

        Airport airport = new Airport();
        airport.setCode(normalizedCode);
        airport.setName(request.getName().trim());
        airport.setCity(request.getCity().trim());
        airport.setCountry(request.getCountry().trim());

        return airportRepository.save(airport);
    }

    public Airport getAirportById(Long id) {

        Airport airport = airportRepository.findById(id).orElse(null);

        if (airport == null) {
            throw new ResponseStatusException(
                    NOT_FOUND,
                    "Airport with id " + id + " not found"
            );
        }
        return airport;
    }

}


