package com.sat.springboot.challenge.service;

import com.sat.springboot.challenge.model.VehicleRequest;
import com.sat.springboot.challenge.model.VehicleResponse;

import java.util.List;


public interface VehicleService {
    long addVehicle(VehicleRequest vehicle);

    long updateVehicle(VehicleRequest vehicle);

    VehicleResponse getVehicleById(long vehicleId);

    long deleteVehicle(long vehicleId);

    List<VehicleResponse> getAllVehicles();
}
