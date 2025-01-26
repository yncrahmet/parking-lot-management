package com.parking.payment_service.dtos.request;

import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PaymentUpdateRequest {
    private String paymentMethod;
    private String paymentStatus;
}
