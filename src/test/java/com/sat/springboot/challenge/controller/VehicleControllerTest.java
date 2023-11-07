package com.sat.springboot.challenge.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sat.springboot.challenge.model.VehicleRequest;
import com.sat.springboot.challenge.model.VehicleResponse;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;

import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class VehicleControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    int randomServerPort;

    static long vehicleId;

    @Test
    @Order(1)
    public void testAddVehicle() throws URISyntaxException, JsonProcessingException {
        final String baseUrl = "http://localhost:" + randomServerPort + "/vehicles";

        VehicleRequest vehicleRequest = new VehicleRequest();
        vehicleRequest.setMake("Hyundai");
        vehicleRequest.setModel("i20");
        vehicleRequest.setYear(2022);

        HttpHeaders headers = new HttpHeaders();

        HttpEntity<VehicleRequest> request = new HttpEntity<>(vehicleRequest, headers);
        ResponseEntity<String> result = this.restTemplate.postForEntity(new URI(baseUrl), request, String.class);
        vehicleId = new ObjectMapper().reader().forType(Long.class).readValue(result.getBody());

        System.out.println("Added vehicle with vehicle id: " + vehicleId);
        assertNotNull(vehicleId);
        assertEquals(201, result.getStatusCodeValue());
    }

    @Test
    @Order(2)
    public void testUpdateVehicle() throws URISyntaxException, JsonProcessingException {
        final String baseUrl = "http://localhost:" + randomServerPort + "/vehicles";

        VehicleRequest vehicleRequest = new VehicleRequest();
        vehicleRequest.setId(vehicleId);
        vehicleRequest.setMake("Hyundai");
        vehicleRequest.setModel("i30");
        vehicleRequest.setYear(2023);

        HttpHeaders headers = new HttpHeaders();

        HttpEntity<VehicleRequest> request = new HttpEntity<>(vehicleRequest, headers);
        ResponseEntity<String> response = restTemplate.exchange(baseUrl, HttpMethod.PUT, request, String.class);

        assertNotNull(new ObjectMapper().reader().forType(Long.class).readValue(response.getBody()));
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    @Order(3)
    public void testGetAllVehicles() throws URISyntaxException, JsonProcessingException {
        final String baseUrl = "http://localhost:" + randomServerPort + "/vehicles";

        ResponseEntity<String> result = this.restTemplate.getForEntity(new URI(baseUrl), String.class);
        List<VehicleResponse> vehicleList = new ObjectMapper().reader().forType(List.class).readValue(result.getBody());

        assertFalse(vehicleList.isEmpty(), "Vehicle list is not empty");
        assertEquals(200, result.getStatusCodeValue());
    }

    @Test
    @Order(4)
    public void testDeleteVehicle() throws URISyntaxException, JsonProcessingException {
        final String baseUrl = "http://localhost:" + randomServerPort + "/vehicles/" + vehicleId ;
        restTemplate.delete(new URI(baseUrl));
    }
}