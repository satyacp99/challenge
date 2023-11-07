package com.sat.springboot.challenge.model;

import lombok.Data;

@Data
public class VehicleRequest {
    private long id;
    private int year;
    private String make;
    private String model;
}
