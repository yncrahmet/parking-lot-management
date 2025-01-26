package com.parking.payment_service.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "payments")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id", nullable = false, unique = true)
    @Schema(name = "Payment ID", example = "1", required = true, nullable = false)
    private Long paymentId;

    @Column(name = "reservation_id")
    @Schema(name = "Reservation ID", example = "1", required = true, nullable = false)
    private Long reservationId;

    @Column(name = "amount", nullable = false)
    @Schema(name = "Amount", example = "100.00", required = true, nullable = false)
    private Double amount;

    @Column(name = "payment_method", nullable = false)
    @Schema(name = "Payment Method", example = "Credit Card", required = true, nullable = false)
    private String paymentMethod;

    @Column(name = "payment_status", nullable = false)
    @Schema(name = "Payment Status", example = "Paid", required = true, nullable = false)
    private String paymentStatus;


}
