package com.archisacademy.parking.controllers;

import com.archisacademy.parking.ApiResponse.ApiResponse;
import com.archisacademy.parking.dtos.request.VehicleRequest;
import com.archisacademy.parking.dtos.request.VehicleUpdateRequest;
import com.archisacademy.parking.dtos.response.VehicleResponse;
import com.archisacademy.parking.services.abstracts.VehicleService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {

    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<VehicleResponse>> save(@Valid @RequestBody VehicleRequest vehicleRequest) {
        ApiResponse<VehicleResponse> apiResponse = vehicleService.save(vehicleRequest);
        return ResponseEntity.ok(apiResponse);
    }

    @PutMapping("/{vehicleId}")
    public ResponseEntity<ApiResponse<VehicleResponse>> update(@PathVariable Long vehicleId, @RequestBody VehicleUpdateRequest vehicleUpdateRequest) {
        ApiResponse<VehicleResponse> apiResponse = vehicleService.update(vehicleId, vehicleUpdateRequest);
        return ResponseEntity.ok(apiResponse);
    }

    @DeleteMapping("/{vehicleId}")
    public ResponseEntity<ApiResponse<String>> delete(@PathVariable Long vehicleId) {
        ApiResponse<String> apiResponse = vehicleService.delete(vehicleId);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/{vehicleId}")
    public ResponseEntity<ApiResponse<VehicleResponse>> get(@PathVariable Long vehicleId) {
        ApiResponse<VehicleResponse> apiResponse = vehicleService.get(vehicleId);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<VehicleResponse>>> getAll() {
        ApiResponse<List<VehicleResponse>> apiResponse = vehicleService.getAll();
        return ResponseEntity.ok(apiResponse);
    }

}
