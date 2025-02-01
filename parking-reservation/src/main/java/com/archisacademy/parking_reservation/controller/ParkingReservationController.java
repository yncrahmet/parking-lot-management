package com.archisacademy.parking_reservation.controller;

import com.archisacademy.parking_reservation.apiResponse.ApiResponse;
import com.archisacademy.parking_reservation.dto.request.ParkingReservationRequest;
import com.archisacademy.parking_reservation.dto.request.ParkingReservationUpdateRequest;
import com.archisacademy.parking_reservation.dto.response.ParkingReservationResponse;
import com.archisacademy.parking_reservation.service.abstracts.ParkingReservationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/parking/reservation")
@Tag(name = "Parking Reservation", description = "API endpoints for managing parking reservation, including creation, update, deletion, retrieval, and listing of parking reservations records.")
@RequiredArgsConstructor
public class ParkingReservationController {
    private final ParkingReservationService parkingReservationService;

    @Operation(summary = "Create new Parking Reservation" ,description = "create new parking reservation")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "HTTP Status 201 Created" ,content = { @Content(schema = @Schema(implementation = ParkingReservationResponse.class), mediaType = "application/json") } ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "HTTP Status 400 Bad Request", content = { @Content(schema = @Schema()) })
    })
    @PostMapping
    public ResponseEntity<ApiResponse<ParkingReservationResponse>> createParkingReservation(@Valid @RequestBody ParkingReservationRequest reservationRequest) {
      ApiResponse response=parkingReservationService.addParkingReservation(reservationRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Get a parking reservation", description = "Get an existing parking reservation")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "HTTP Status 200 OK", content = { @Content(schema = @Schema(implementation = ParkingReservationRequest.class), mediaType = "application/json") } ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "HTTP Status 400 Bad Request", content = { @Content(schema = @Schema()) })
    })
    @GetMapping("{id}")
    public ResponseEntity<ApiResponse<ParkingReservationRequest>> getParkingReservationById(@PathVariable  Long id) {
        ApiResponse response = parkingReservationService.getParkingReservationById(id);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Update a parking reservation", description = "Update an existing parking reservation")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "HTTP Status 200 OK", content = { @Content(schema = @Schema(implementation = ParkingReservationResponse.class), mediaType = "application/json") } ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "HTTP Status 400 Bad Request", content = { @Content(schema = @Schema()) })
    })
    @PutMapping("{id}")
    public ResponseEntity<ApiResponse<ParkingReservationResponse>> updateParkingReservation(@PathVariable Long id,
                                                                                             @RequestBody ParkingReservationUpdateRequest reservationRequest) {
        ApiResponse response=    parkingReservationService.updateParkingReservation(id,reservationRequest);
    return ResponseEntity.ok(response);

    }

    @Operation(summary = "Delete a parking reservation", description = "Delete an existing parking reservation")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "HTTP Status 200 OK", content = { @Content(schema = @Schema()) } ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "HTTP Status 400 Bad Request", content = { @Content(schema = @Schema()) })
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>>  deleteParkingReservation(@PathVariable Long id) {
       ApiResponse response= parkingReservationService.deleteParking(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
    }
}
