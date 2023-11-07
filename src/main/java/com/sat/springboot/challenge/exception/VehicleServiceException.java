package com.sat.springboot.challenge.exception;

import lombok.Data;

@Data
public class VehicleServiceException extends RuntimeException {

    private String errorCode;

    public VehicleServiceException(String errorCode, String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode;
    }
}
