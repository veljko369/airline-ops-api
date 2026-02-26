package com.veljko.airline_ops.service;

import com.veljko.airline_ops.dto.CreateFlightRequest;
import com.veljko.airline_ops.model.*;
import com.veljko.airline_ops.repository.AircraftRepository;
import com.veljko.airline_ops.repository.AirportRepository;
import com.veljko.airline_ops.repository.FlightRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ExtendWith(MockitoExtension.class)
class FlightServiceTest {

    @Mock
    private AircraftRepository aircraftRepository;

    @Mock
    private AirportRepository airportRepository;

    @Mock
    private FlightRepository flightRepository;

    @InjectMocks
    private FlightService flightService;

    @Captor
    private ArgumentCaptor<Flight> flightCaptor;

    @Test
    void createFlight_shouldSaveFlight_withDefaultStatusWhenNull() {
        CreateFlightRequest req = new CreateFlightRequest();
        req.setFlightNumber("JU540");
        req.setAircraftId(1L);
        req.setOriginAirportId(10L);
        req.setDestinationAirportId(20L);
        req.setScheduledDeparture(LocalDateTime.of(2025, 12, 13, 8, 30));
        req.setScheduledArrival(LocalDateTime.of(2025, 12, 13, 10, 45));
        req.setGate("A4");
        req.setStatus(null);

        Aircraft aircraft = new Aircraft();
        aircraft.setId(1L);

        Airport origin = new Airport();
        origin.setId(10L);

        Airport dest = new Airport();
        dest.setId(20L);

        when(aircraftRepository.findById(1L)).thenReturn(Optional.of(aircraft));
        when(airportRepository.findById(10L)).thenReturn(Optional.of(origin));
        when(airportRepository.findById(20L)).thenReturn(Optional.of(dest));
        when(flightRepository.save(any(Flight.class))).thenAnswer(inv -> inv.getArgument(0));

        Flight saved = flightService.createFlight(req);

        assertNotNull(saved);
        assertEquals(FlightStatus.SCHEDULED, saved.getStatus());

        verify(flightRepository).save(flightCaptor.capture());
        assertEquals(aircraft, flightCaptor.getValue().getAircraft());
        assertEquals(origin, flightCaptor.getValue().getOriginAirport());
        assertEquals(dest, flightCaptor.getValue().getDestinationAirport());
    }

    @Test
    void createFlight_shouldThrow400_whenAircraftNotFound() {
        CreateFlightRequest req = new CreateFlightRequest();
        req.setAircraftId(999L);
        req.setOriginAirportId(10L);
        req.setDestinationAirportId(20L);
        req.setScheduledDeparture(LocalDateTime.of(2025, 12, 13, 8, 30));
        req.setScheduledArrival(LocalDateTime.of(2025, 12, 13, 10, 45));
        req.setFlightNumber("JU999");

        when(aircraftRepository.findById(999L)).thenReturn(Optional.empty());

        ResponseStatusException ex = assertThrows(ResponseStatusException.class,
                () -> flightService.createFlight(req));

        assertEquals(BAD_REQUEST, ex.getStatusCode());
        verify(flightRepository, never()).save(any());
    }

    @Test
    void getFlightById_shouldThrow404_whenNotFound() {
        when(flightRepository.findById(1L)).thenReturn(Optional.empty());

        ResponseStatusException ex = assertThrows(ResponseStatusException.class,
                () -> flightService.getFlightById(1L));

        assertEquals(NOT_FOUND, ex.getStatusCode());
    }

    @Test
    void updateWeightBalance_shouldThrow400_whenNegativeValues() {
        Flight flight = new Flight();
        flight.setId(1L);

        when(flightRepository.findById(1L)).thenReturn(Optional.of(flight));

        ResponseStatusException ex = assertThrows(ResponseStatusException.class,
                () -> flightService.updateWeightBalance(1L, -1, null, null));

        assertEquals(BAD_REQUEST, ex.getStatusCode());
        verify(flightRepository, never()).save(any());
    }

    @Test
    void updateStatus_shouldSaveNewStatus() {
        Flight flight = new Flight();
        flight.setId(1L);
        flight.setStatus(FlightStatus.SCHEDULED);

        when(flightRepository.findById(1L)).thenReturn(Optional.of(flight));
        when(flightRepository.save(any(Flight.class))).thenAnswer(inv -> inv.getArgument(0));

        Flight updated = flightService.updateStatus(1L, FlightStatus.DELAYED);

        assertEquals(FlightStatus.DELAYED, updated.getStatus());
        verify(flightRepository, times(1)).save(flight);
    }
}