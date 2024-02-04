package com.asteway.inventory_management_service.repositories;

import com.asteway.inventory_management_service.entities.Item;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface InventoryRepository extends MongoRepository<Item, String> {
}
