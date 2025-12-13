package com.veljko.airline_ops.repository;

import com.veljko.airline_ops.model.Aircraft;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AircraftRepository extends JpaRepository<Aircraft,Long> {
    boolean existsByRegistration(String registration);
}
