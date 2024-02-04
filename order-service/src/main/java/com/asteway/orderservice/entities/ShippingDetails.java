package com.asteway.orderservice.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
@Builder
@AllArgsConstructor
public class ShippingDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long shippingId;

    @Embedded
    private Address shippingAddress;
    private String trackingNumber;

    public ShippingDetails(){
        this.trackingNumber = generateTrckingNumber();
    }

    private String generateTrckingNumber() {
        return UUID.randomUUID().toString().replace("-", "").substring(0, 12);
    }
}
