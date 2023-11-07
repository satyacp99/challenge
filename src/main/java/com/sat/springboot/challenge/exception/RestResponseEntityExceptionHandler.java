package com.sat.springboot.challenge.exception;


import com.sat.springboot.challenge.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(VehicleServiceException.class)
    public ResponseEntity<ErrorResponse> handleVehicleNotFoundException(VehicleServiceException exception) {
        return new ResponseEntity<>(ErrorResponse.builder().
                errorMessage(exception.getMessage())
                .errorCode(exception.getErrorCode())
                .build(), HttpStatus.NOT_FOUND);
    }
}
