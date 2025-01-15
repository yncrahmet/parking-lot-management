package com.archisacademy.parking.controllers;

import com.archisacademy.parking.ApiResponse.ApiResponse;
import com.archisacademy.parking.dtos.request.VehicleRequest;
import com.archisacademy.parking.dtos.request.VehicleUpdateRequest;
import com.archisacademy.parking.dtos.response.VehicleResponse;
import com.archisacademy.parking.services.abstracts.VehicleService;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {

    private final VehicleService vehicleService;
    private final Logger logger = LoggerFactory.getLogger(VehicleController.class);

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<VehicleResponse>> save(@Valid @RequestBody VehicleRequest vehicleRequest) {
        logger.info("Request to save vehicle: {}", vehicleRequest);
        ApiResponse<VehicleResponse> apiResponse = vehicleService.save(vehicleRequest);
        logger.info("Vehicle saved: {}", apiResponse);
        return ResponseEntity.ok(apiResponse);
    }

    @PutMapping("/{vehicleId}")
    public ResponseEntity<ApiResponse<VehicleResponse>> update(@PathVariable Long vehicleId, @RequestBody VehicleUpdateRequest vehicleUpdateRequest) {
        logger.info("Request to update vehicle: {}", vehicleUpdateRequest);
        ApiResponse<VehicleResponse> apiResponse = vehicleService.update(vehicleId, vehicleUpdateRequest);
        logger.info("Vehicle updated: {}", apiResponse);
        return ResponseEntity.ok(apiResponse);
    }

    @DeleteMapping("/{vehicleId}")
    public ResponseEntity<ApiResponse<String>> delete(@PathVariable Long vehicleId) {
        logger.info("Request to delete vehicle: {}", vehicleId);
        ApiResponse<String> apiResponse = vehicleService.delete(vehicleId);
        logger.info("Vehicle deleted: {}", apiResponse);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/{vehicleId}")
    public ResponseEntity<ApiResponse<VehicleResponse>> get(@PathVariable Long vehicleId) {
        logger.info("Request to get vehicle: {}", vehicleId);
        ApiResponse<VehicleResponse> apiResponse = vehicleService.get(vehicleId);
        logger.info("Vehicle found: {}", apiResponse);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<VehicleResponse>>> getAll() {
        logger.info("Request to get all vehicles");
        ApiResponse<List<VehicleResponse>> apiResponse = vehicleService.getAll();
        logger.info("Fetched all vehicles successfully, total count: {}", apiResponse.getData().size());
        return ResponseEntity.ok(apiResponse);
    }

}
