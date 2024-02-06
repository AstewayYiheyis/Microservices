package com.asteway.shippingmanagementservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ShipmentDTO {
    private UserEntityDTO sender;
    private UserEntityDTO receiver;
    private AddressDTO shippingAddress;
}
