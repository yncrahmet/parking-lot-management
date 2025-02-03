package com.archisacademy.parking.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "vehicles")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(name = "Vehicle ID", example = "1", required = true, nullable = false)
    private Long id;

    @Column(name = "registration_number", unique = true)
    @Schema(name = "Registration Number", example = "ABC123", required = true, nullable = false)
    private String registrationNumber;

    @Column(name = "type")
    @Schema(name = "Type", example = "Car", required = false,nullable = true)
    private String type;

    @Column(name = "is_active")
    @Schema(name = "Is Active", example = "true", required = true, nullable = false)
    private boolean isActive = true;

    @CreationTimestamp
    @Column(name = "registration_date", updatable = false)
    @Schema(name = "Registration Date", example = "2022-01-01", required = true, nullable = false)
    private Timestamp registrationDate;

}
