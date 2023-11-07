package com.sat.springboot.challenge.controller;

import com.sat.springboot.challenge.model.VehicleRequest;
import com.sat.springboot.challenge.model.VehicleResponse;
import com.sat.springboot.challenge.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {

    private final VehicleService vehicleService;

    @Autowired
    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @PostMapping
    public ResponseEntity<Long> addVehicle(@RequestBody VehicleRequest vehicle) {
        long vehicleId = vehicleService.addVehicle(vehicle);
        return new ResponseEntity<>(vehicleId, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Long> updateVehicle(@RequestBody VehicleRequest vehicle) {
        long vehicleId = vehicleService.updateVehicle(vehicle);
        return new ResponseEntity<>(vehicleId, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<VehicleResponse>> getAllVehicles() {
        List<VehicleResponse> vehicleList = vehicleService.getAllVehicles();
        return new ResponseEntity<>(vehicleList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VehicleResponse> getVehicleById(@PathVariable("id") long vehicleId) {
        VehicleResponse vehicleResponse = vehicleService.getVehicleById(vehicleId);
        return new ResponseEntity<>(vehicleResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteVehicle(@PathVariable("id") Long vehicleId) {
        vehicleService.deleteVehicle(vehicleId);
        return new ResponseEntity<>(vehicleId, HttpStatus.OK);
    }
}
