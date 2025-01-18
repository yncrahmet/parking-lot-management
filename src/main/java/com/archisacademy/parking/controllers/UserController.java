package com.archisacademy.parking.controllers;

import com.archisacademy.parking.dtos.request.UserRequest;
import com.archisacademy.parking.dtos.request.UserUpdateRequest;
import com.archisacademy.parking.dtos.response.UserResponse;
import com.archisacademy.parking.dtos.response.UserUpdateResponse;
import com.archisacademy.parking.services.abstracts.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/save")
    @Operation(summary = "Save a user", description = "Save a new user")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "HTTP Status 201 Created" ,content = { @Content(schema = @Schema(implementation = UserResponse.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "403", description = "HTTP Status 403 Forbidden", content = { @Content(schema = @Schema()) })
    })
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserRequest userRequest) {
        UserResponse response= userService.createUser(userRequest);
        return ResponseEntity.ok()
                .body(response);
    }
    @GetMapping("/list")
    @Operation(summary = "Get all users", description = "Get all existing users")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "HTTP Status 200 OK", content = { @Content(schema = @Schema(implementation = UserResponse.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "403", description = "HTTP Status 403 Forbidden", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "404", description = "HTTP Status 404 Not Found", content = { @Content(schema = @Schema()) })
    })
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        List<UserResponse> response=userService.getAllUsers();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a user", description = "Update an existing user")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "HTTP Status 200 OK", content = { @Content(schema = @Schema(implementation = UserResponse.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "403", description = "HTTP Status 403 Forbidden", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "404", description = "HTTP Status 404 Not Found", content = { @Content(schema = @Schema()) })
    })
    public ResponseEntity<UserUpdateResponse> updateUser(
            @PathVariable Long id,
            @Valid @RequestBody UserUpdateRequest userRequest) {
        UserUpdateResponse response = userService.updateUser(id, userRequest);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a user", description = "Delete an existing user")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "HTTP Status 200 OK", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "403", description = "HTTP Status 403 Forbidden", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "404", description = "HTTP Status 404 Not Found", content = { @Content(schema = @Schema()) })
    })
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
