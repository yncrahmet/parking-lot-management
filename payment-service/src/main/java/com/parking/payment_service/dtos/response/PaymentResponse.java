package com.parking.payment_service.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentResponse {
    private Long paymentId;
    private Long reservationId;
    private Double amount;
    private String paymentMethod;
    private String paymentStatus;
    private LocalDateTime timestamp;
}
