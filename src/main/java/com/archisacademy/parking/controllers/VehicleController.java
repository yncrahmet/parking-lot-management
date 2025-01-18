package com.archisacademy.parking.controllers;

import com.archisacademy.parking.ApiResponse.ApiResponse;
import com.archisacademy.parking.dtos.request.VehicleRequest;
import com.archisacademy.parking.dtos.request.VehicleUpdateRequest;
import com.archisacademy.parking.dtos.response.VehicleResponse;
import com.archisacademy.parking.services.abstracts.VehicleService;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(
            summary = "Save a vehicle",
            description = "Save a new vehicle")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "HTTP Status 201 Created" ,content = { @Content(schema = @Schema(implementation = VehicleResponse.class), mediaType = "application/json") }),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "403", description = "HTTP Status 403 Forbidden", content = { @Content(schema = @Schema()) }),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "HTTP Status 404 Not Found", content = { @Content(schema = @Schema()) }) })
    public ResponseEntity<ApiResponse<VehicleResponse>> save(@Valid @RequestBody VehicleRequest vehicleRequest) {
        ApiResponse<VehicleResponse> apiResponse = vehicleService.save(vehicleRequest);
        return ResponseEntity.ok(apiResponse);
    }

    @PutMapping("/{vehicleId}")
    @Operation(
            summary = "Update a vehicle",
            description = "Update an existing vehicle"
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "HTTP Status 200 OK", content = { @Content(schema = @Schema(implementation = VehicleResponse.class), mediaType = "application/json") }),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "403", description = "HTTP Status 403 Forbidden", content = { @Content(schema = @Schema()) }),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "HTTP Status 404 Not Found", content = { @Content(schema = @Schema()) })
    })
    public ResponseEntity<ApiResponse<VehicleResponse>> update(@PathVariable Long vehicleId, @RequestBody VehicleUpdateRequest vehicleUpdateRequest) {
        ApiResponse<VehicleResponse> apiResponse = vehicleService.update(vehicleId, vehicleUpdateRequest);
        return ResponseEntity.ok(apiResponse);
    }

    @DeleteMapping("/{vehicleId}")
    @Operation(
            summary = "Delete a vehicle",
            description = "Delete an existing vehicle"
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "HTTP Status 200 OK", content = { @Content(schema = @Schema()) }),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "403", description = "HTTP Status 403 Forbidden", content = { @Content(schema = @Schema()) }),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "HTTP Status 404 Not Found", content = { @Content(schema = @Schema()) })
    })
    public ResponseEntity<ApiResponse<String>> delete(@PathVariable Long vehicleId) {
        ApiResponse<String> apiResponse = vehicleService.delete(vehicleId);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/{vehicleId}")
    @Operation(
            summary = "Get a vehicle",
            description = "Get an existing vehicle"
    )
    @ApiResponses({
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "HTTP Status 200 OK", content = { @Content(schema = @Schema(implementation = VehicleResponse.class), mediaType = "application/json") }),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "403", description = "HTTP Status 403 Forbidden", content = { @Content(schema = @Schema()) }),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "HTTP Status 404 Not Found", content = { @Content(schema = @Schema()) })
    })
    public ResponseEntity<ApiResponse<VehicleResponse>> get(@PathVariable Long vehicleId) {
        ApiResponse<VehicleResponse> apiResponse = vehicleService.get(vehicleId);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/all")
    @Operation(
            summary = "Get all vehicles",
            description = "Get all existing vehicles"
    )
    @ApiResponses({
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "HTTP Status 200 OK", content = { @Content(schema = @Schema(implementation = VehicleResponse.class), mediaType = "application/json") }),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "403", description = "HTTP Status 403 Forbidden", content = { @Content(schema = @Schema()) }),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "HTTP Status 404 Not Found", content = { @Content(schema = @Schema()) })
    })
    public ResponseEntity<ApiResponse<List<VehicleResponse>>> getAll() {
        ApiResponse<List<VehicleResponse>> apiResponse = vehicleService.getAll();
        return ResponseEntity.ok(apiResponse);
    }

}
