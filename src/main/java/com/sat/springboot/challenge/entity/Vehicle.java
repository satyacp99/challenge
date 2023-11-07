package com.sat.springboot.challenge.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "VEHICLE_YEAR")
    private int year;
    @Column(name = "MAKE")
    private String make;
    @Column(name = "MODEL")
    private String model;
}
