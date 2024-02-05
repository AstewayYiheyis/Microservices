package com.asteway.inventory_management_service.services;

import com.asteway.inventory_management_service.entities.Item;
import com.asteway.inventory_management_service.exceptions.ItemNotFoundException;
import com.asteway.inventory_management_service.repositories.InventoryRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@WebMvcTest(InventoryService.class)
public class InventoryServiceTests {
    @Autowired
    private InventoryService inventoryService;
    @MockBean
    private InventoryRepository inventoryRepository;

    @Test
    public void testGetAllOrders() {
        // mock data
        List<Item> items = List.of(new Item(),
                new Item(),
                new Item());

        // mock the repository behavior
        Mockito.when(inventoryRepository.findAll()).thenReturn(items);

        assertEquals(3, inventoryService.getItems().size());
    }

    @Test
    public void testGetOrderById() throws ItemNotFoundException {
        List<Item> items = List.of(Item.builder().id("1").name("Phone").build(),
                Item.builder().id("2").name("Book").build());

        Mockito.when(inventoryRepository.findById("1")).thenReturn(Optional.of(items.get(0)));
        Mockito.when(inventoryRepository.findById("2")).thenReturn(Optional.of(items.get(1)));

        assertEquals("Phone", inventoryService.getItemById("1").getName());
        assertEquals("Book", inventoryService.getItemById("2").getName());
    }

    @Test
    public void testDeleteOrder() {
        String itemId = "1";

        Mockito.doNothing().when(inventoryRepository).deleteById(itemId);
        Mockito.when(inventoryRepository.existsById(itemId)).thenReturn(true);

        inventoryService.deleteItem(itemId);

        Mockito.verify(inventoryRepository, Mockito.times(1)).deleteById(itemId);
    }
}

