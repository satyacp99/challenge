package com.sat.springboot.challenge.repository;

import com.sat.springboot.challenge.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
}
