package com.asteway.orderservice.dtos;

import com.asteway.orderservice.entities.OrderEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemDTO {
    private String itemName;
    private int quantity;
    private OrderEntity order;
}
