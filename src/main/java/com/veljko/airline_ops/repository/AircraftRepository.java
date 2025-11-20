package com.veljko.airline_ops.repository;

import com.veljko.airline_ops.model.Aircraft;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AircraftRepository extends JpaRepository<Aircraft,Long> {

}
