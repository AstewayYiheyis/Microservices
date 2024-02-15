package com.asteway.orderservice.dtos;

import com.asteway.orderservice.entities.Item;
import com.asteway.orderservice.entities.ShippingDetails;
import com.asteway.orderservice.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDTO {
    private String customerName;
    private String email;
    private List<Item> items;
    private Status status;
    private ShippingDetails shippingDetails;
}
