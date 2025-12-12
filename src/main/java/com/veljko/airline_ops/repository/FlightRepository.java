package com.veljko.airline_ops.repository;

import com.veljko.airline_ops.model.Flight;
import com.veljko.airline_ops.model.FlightStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {

    List<Flight> findByStatus(FlightStatus status);

    List<Flight> findByOriginAirport_Code(String code);

    List<Flight> findByDestinationAirport_Code(String code);

    List<Flight> findByAircraftId(Long aircraftId);

}


