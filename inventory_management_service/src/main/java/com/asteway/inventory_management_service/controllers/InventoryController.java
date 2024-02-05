package com.asteway.inventory_management_service.controllers;

import com.asteway.inventory_management_service.dtos.ItemDTO;
import com.asteway.inventory_management_service.exceptions.ItemNotFoundException;
import com.asteway.inventory_management_service.services.InventoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventory")
public class InventoryController {
    private InventoryService inventoryService;

    public InventoryController(final InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping
    public ResponseEntity<List<ItemDTO>> getItems(){
        return new ResponseEntity<>(inventoryService.getItems(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemDTO> getItemById(@PathVariable String id) throws ItemNotFoundException {
        return new ResponseEntity<>(inventoryService.getItemById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ItemDTO> addItem(@RequestBody ItemDTO itemDTO) {
        return new ResponseEntity<>(inventoryService.addItem(itemDTO), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemDTO> updateItem(@RequestBody ItemDTO itemDTO, @PathVariable String id) throws ItemNotFoundException {
        return new ResponseEntity<>(inventoryService.updateItem(id, itemDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable String id) throws ItemNotFoundException {
        inventoryService.deleteItem(id);

        return ResponseEntity.noContent().build();
    }
}
