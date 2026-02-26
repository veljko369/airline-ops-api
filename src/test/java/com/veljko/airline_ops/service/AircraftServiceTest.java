package com.veljko.airline_ops.service;

import com.veljko.airline_ops.dto.CreateAircraftRequest;
import com.veljko.airline_ops.model.Aircraft;
import com.veljko.airline_ops.repository.AircraftRepository;
import com.veljko.airline_ops.repository.FlightRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ExtendWith(MockitoExtension.class)
class AircraftServiceTest {

    @Mock
    private AircraftRepository aircraftRepository;

    @Mock
    private FlightRepository flightRepository;

    @InjectMocks
    private AircraftService aircraftService;

    @Captor
    private ArgumentCaptor<Aircraft> aircraftCaptor;

    @Test
    void createAircraft_shouldSaveNormalizedAircraft() {
        CreateAircraftRequest req = new CreateAircraftRequest();
        req.setRegistration(" yu-aph ");
        req.setType(" a320 ");
        req.setManufacturer("Airbus");
        req.setSeatingCapacity(180);
        req.setMaxTakeoffWeightKg(73500);

        when(aircraftRepository.existsByRegistration("YU-APH")).thenReturn(false);
        when(aircraftRepository.save(any(Aircraft.class))).thenAnswer(inv -> inv.getArgument(0));

        Aircraft saved = aircraftService.createAircraft(req);

        assertEquals("YU-APH", saved.getRegistration());
        assertEquals("A320", saved.getType());
        assertEquals("Airbus", saved.getManufacturer());
        assertEquals(180, saved.getSeatingCapacity());
        assertEquals(73500, saved.getMaxTakeoffWeightKg());

        verify(aircraftRepository).save(aircraftCaptor.capture());
        assertEquals("YU-APH", aircraftCaptor.getValue().getRegistration());
    }

    @Test
    void createAircraft_shouldThrow400_whenRegistrationExists() {
        CreateAircraftRequest req = new CreateAircraftRequest();
        req.setRegistration("YU-APH");
        req.setType("A320");
        req.setManufacturer("Airbus");
        req.setSeatingCapacity(180);
        req.setMaxTakeoffWeightKg(73500);

        when(aircraftRepository.existsByRegistration("YU-APH")).thenReturn(true);

        ResponseStatusException ex = assertThrows(ResponseStatusException.class,
                () -> aircraftService.createAircraft(req));

        assertEquals(BAD_REQUEST, ex.getStatusCode());
        verify(aircraftRepository, never()).save(any());
    }

    @Test
    void getAircraftById_shouldThrow404_whenNotFound() {
        when(aircraftRepository.findById(1L)).thenReturn(Optional.empty());

        ResponseStatusException ex = assertThrows(ResponseStatusException.class,
                () -> aircraftService.getAircraftById(1L));

        assertEquals(NOT_FOUND, ex.getStatusCode());
    }

    @Test
    void deleteAircraft_shouldThrow400_whenAssignedToFlights() {
        Aircraft aircraft = new Aircraft();
        aircraft.setId(10L);
        aircraft.setRegistration("YU-TEST");

        when(aircraftRepository.findById(10L)).thenReturn(Optional.of(aircraft));
        when(flightRepository.existsByAircraft_Id(10L)).thenReturn(true);

        ResponseStatusException ex = assertThrows(ResponseStatusException.class,
                () -> aircraftService.deleteAircraft(10L));

        assertEquals(BAD_REQUEST, ex.getStatusCode());
        verify(aircraftRepository, never()).delete(any());
    }

    @Test
    void deleteAircraft_shouldDelete_whenNotAssigned() {
        Aircraft aircraft = new Aircraft();
        aircraft.setId(11L);
        aircraft.setRegistration("YU-OK");

        when(aircraftRepository.findById(11L)).thenReturn(Optional.of(aircraft));
        when(flightRepository.existsByAircraft_Id(11L)).thenReturn(false);

        aircraftService.deleteAircraft(11L);

        verify(aircraftRepository, times(1)).delete(aircraft);
    }
}