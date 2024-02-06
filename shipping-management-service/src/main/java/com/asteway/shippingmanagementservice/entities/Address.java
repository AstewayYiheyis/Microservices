package com.asteway.shippingmanagementservice.entities;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Embeddable
@Data
public class Address {
    private String street;
    private String city;
    private String state;
    private String postalCode;
    private String country;
}
