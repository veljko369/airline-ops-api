package com.veljko.airline_ops.controller;

import com.veljko.airline_ops.model.Airport;
import com.veljko.airline_ops.service.AirportService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/airports")
public class AirportController {

    private final AirportService airportService;

    public AirportController(AirportService airportService){
        this.airportService = airportService;
    }

    @GetMapping
    public List<Airport> getAllAirports(){
        return airportService.getAllAirports();
    }

    @PostMapping
    public Airport createAirport(@RequestBody Airport airport){
        return airportService.createAirport(airport);
    }

    @GetMapping("/{id}")
    public Airport getAirportById(@PathVariable Long id){
        return airportService.getAirportById(id);
    }

}
