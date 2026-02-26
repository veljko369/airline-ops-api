package com.veljko.airline_ops.service;

import com.veljko.airline_ops.dto.CreateAirportRequest;
import com.veljko.airline_ops.model.Airport;
import com.veljko.airline_ops.repository.AirportRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ExtendWith(MockitoExtension.class)
class AirportServiceTest {

    @Mock
    private AirportRepository airportRepository;

    @InjectMocks
    private AirportService airportService;

    @Test
    void getAllAirports_shouldReturnList() {
        when(airportRepository.findAll()).thenReturn(List.of(new Airport(), new Airport()));

        List<Airport> result = airportService.getAllAirports();

        assertEquals(2, result.size());
        verify(airportRepository, times(1)).findAll();
    }

    @Test
    void getAirportById_shouldThrow404_whenNotFound() {
        when(airportRepository.findById(1L)).thenReturn(Optional.empty());

        ResponseStatusException ex = assertThrows(ResponseStatusException.class,
                () -> airportService.getAirportById(1L));

        assertEquals(NOT_FOUND, ex.getStatusCode());
        verify(airportRepository, times(1)).findById(1L);
    }

    @Test
    void createAirport_shouldSaveAndReturnAirport() {
        CreateAirportRequest request = new CreateAirportRequest();
        request.setCode("BEG");
        request.setName("Belgrade Nikola Tesla Airport");
        request.setCity("Belgrade");
        request.setCountry("Serbia");

        Airport savedAirport = new Airport();
        savedAirport.setId(1L);
        savedAirport.setCode("BEG");
        savedAirport.setName(request.getName());
        savedAirport.setCity(request.getCity());
        savedAirport.setCountry(request.getCountry());

        when(airportRepository.existsByCode("BEG")).thenReturn(false);

        when(airportRepository.save(any(Airport.class))).thenReturn(savedAirport);

        Airport result = airportService.createAirport(request);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("BEG", result.getCode());

        verify(airportRepository, times(1)).save(any(Airport.class));
    }
}