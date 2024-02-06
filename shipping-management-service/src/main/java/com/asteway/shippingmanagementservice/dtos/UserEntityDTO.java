package com.asteway.shippingmanagementservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEntityDTO {
    private String firstName;
    private String lastName;
    private String email;
    private AddressDTO address;
    private String phoneNumber;
}
