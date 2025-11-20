package com.veljko.airline_ops.repository;

import com.veljko.airline_ops.model.Airport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirportRepository extends JpaRepository<Airport, Long> {
}
