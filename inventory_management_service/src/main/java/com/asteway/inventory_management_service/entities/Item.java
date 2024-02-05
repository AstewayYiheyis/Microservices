package com.asteway.inventory_management_service.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "inventory")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    @Id
    private String id;
    private String name;
    private double price;
    private int stockQuantity;
}
