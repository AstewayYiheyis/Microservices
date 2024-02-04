package com.asteway.inventory_management_service.services;

import com.asteway.inventory_management_service.entities.Item;
import com.asteway.inventory_management_service.exceptions.ItemNotFoundException;
import com.asteway.inventory_management_service.repositories.InventoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventoryService {
    private InventoryRepository inventoryRepository;

    public InventoryService(final InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    public List<Item> getItems() {
        return inventoryRepository.findAll();
    }

    public Item getItemById(String id) throws ItemNotFoundException {
        Optional<Item> item = inventoryRepository.findById(id);

        if(!item.isPresent()){
            throw new ItemNotFoundException("There is no item with the provided ID!");
        }

        return item.get();
    }

    public Item addItem(Item item){
        return inventoryRepository.save(item);
    }

    public Item updateItem(String itemId, Item updateItem) throws ItemNotFoundException {
        if(inventoryRepository.existsById(itemId)){
            updateItem.setId(itemId);

            return inventoryRepository.save(updateItem);
        }
        else{
            throw new ItemNotFoundException("Item with ID " + itemId + " not found");
        }
    }

    public void deleteItem(String itemId) throws ItemNotFoundException {
        if(inventoryRepository.existsById(itemId)){
            inventoryRepository.deleteById(itemId);
        }
        else{
            throw new ItemNotFoundException("Item with ID " + itemId + " not found");
        }
    }
}
