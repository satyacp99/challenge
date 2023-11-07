package com.sat.springboot.challenge.service;

import com.sat.springboot.challenge.entity.Vehicle;
import com.sat.springboot.challenge.exception.VehicleServiceException;
import com.sat.springboot.challenge.model.VehicleRequest;
import com.sat.springboot.challenge.model.VehicleResponse;
import com.sat.springboot.challenge.repository.VehicleRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class VehicleServiceImpl implements VehicleService {
    private final VehicleRepository vehicleRepository;

      @Autowired
    public VehicleServiceImpl(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public long addVehicle(VehicleRequest vehicle) {
        log.info("Adding vehicle");
        Vehicle vehicleEntity = Vehicle.builder()
                .year(vehicle.getYear())
                .make(vehicle.getMake())
                .model(vehicle.getModel())
                .build();
        vehicleEntity = vehicleRepository.save(vehicleEntity);
        log.info("Vehicle added: " + vehicle.getMake());
        return vehicleEntity.getId();
    }

    @Override
    public long updateVehicle(VehicleRequest vehicle) {
        log.info("Updating product");
        Vehicle vehicleEntity = Vehicle.builder()
                .id(vehicle.getId())
                .year(vehicle.getYear())
                .make(vehicle.getMake())
                .model(vehicle.getModel())
                .build();
        vehicleRepository.save(vehicleEntity);
        log.info("Vehicle updated: " + vehicle.getId());
        return vehicleEntity.getId();
    }

    @Override
    public VehicleResponse getVehicleById(long vehicleId) {
        log.info("inside getVehicleById");
        Vehicle vehicle = vehicleRepository.findById(vehicleId).
                orElseThrow(() -> new VehicleServiceException("VEHICLE_NOT_FOUND", "Vehicle with given id not found" + vehicleId));
        VehicleResponse vehicleResponse = new VehicleResponse();
        BeanUtils.copyProperties(vehicle, vehicleResponse);
        return vehicleResponse;
    }

    public List<VehicleResponse> getAllVehicles() {
        log.info("getVehicles");
        List<Vehicle> vehicleList = vehicleRepository.findAll();
        List<VehicleResponse> vehicles = vehicleList.stream().map(vehicle ->
        {
            VehicleResponse vehicleR = new VehicleResponse();
            BeanUtils.copyProperties(vehicle, vehicleR);
            return vehicleR;
        }).toList();
        return vehicles;
    }

    @Override
    public long deleteVehicle(long vehicleId) {
        vehicleRepository.deleteById(vehicleId);
        log.info("Vehicle deleted: " + vehicleId);
        return vehicleId;
    }
}
