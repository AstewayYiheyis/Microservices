package com.asteway.orderservice.models;

import com.asteway.orderservice.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderEvent {
    private String email;
    private Status status;
}
