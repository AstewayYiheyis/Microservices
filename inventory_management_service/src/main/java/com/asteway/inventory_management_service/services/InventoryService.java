package com.asteway.inventory_management_service.services;

import com.asteway.inventory_management_service.dtos.ItemDTO;
import com.asteway.inventory_management_service.entities.Item;
import com.asteway.inventory_management_service.exceptions.ItemNotFoundException;
import com.asteway.inventory_management_service.repositories.InventoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static com.asteway.inventory_management_service.utils.ItemConverter.getItemDTO;
import static com.asteway.inventory_management_service.utils.ItemConverter.getItemFromDTO;

@Service
public class InventoryService {
    private InventoryRepository inventoryRepository;

    private Logger logger = LoggerFactory.getLogger(InventoryService.class);

    public InventoryService(final InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    public List<ItemDTO> getItems() {
        List<ItemDTO> itemDTOS = new ArrayList<>();

        for(Item item:inventoryRepository.findAll()){
            itemDTOS.add(getItemDTO(item));
        }

        return itemDTOS;
    }

    public ItemDTO getItemById(String id) throws ItemNotFoundException {
        Optional<Item> item = inventoryRepository.findById(id);

        if(!item.isPresent()){
            throw new ItemNotFoundException("There is no item with the provided ID!");
        }

        return getItemDTO(item.get());
    }

    public ItemDTO addItem(ItemDTO itemDTO){
        Item item = inventoryRepository.save(getItemFromDTO(itemDTO));

        return getItemDTO(item);
    }

    public ItemDTO updateItem(String itemId, ItemDTO itemDTO){
        Item item = getItemFromDTO(itemDTO);

        item.setId(itemId);

        Item updatedItem = inventoryRepository.save(item);

        return getItemDTO(updatedItem);
    }

    public void deleteItem(String itemId){
        if (inventoryRepository.existsById(itemId)) {
            inventoryRepository.deleteById(itemId);
        } else {
            logger.warn("Attempting to delete a non-existent item");
        }
    }
}
