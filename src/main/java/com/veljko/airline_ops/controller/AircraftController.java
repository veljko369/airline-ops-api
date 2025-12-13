package com.veljko.airline_ops.controller;

import com.veljko.airline_ops.dto.CreateAircraftRequest;
import com.veljko.airline_ops.model.Aircraft;
import com.veljko.airline_ops.service.AircraftService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/aircraft")
public class AircraftController {

    private final AircraftService aircraftService;

    public AircraftController(AircraftService aircraftService) {
        this.aircraftService = aircraftService;
    }

    @GetMapping
    public List<Aircraft> getAllAircraft() {
        return aircraftService.getAllAircraft();
    }

    @PostMapping
    public Aircraft createAircraft(@Valid @RequestBody CreateAircraftRequest request) {
        return aircraftService.createAircraft(request);
    }

    @GetMapping("/{id}")
    public Aircraft getAircraftById(@PathVariable Long id) {
        return aircraftService.getAircraftById(id);
    }
}
