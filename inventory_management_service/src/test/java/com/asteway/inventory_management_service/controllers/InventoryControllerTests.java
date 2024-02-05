package com.asteway.inventory_management_service.controllers;

import com.asteway.inventory_management_service.dtos.ItemDTO;
import com.asteway.inventory_management_service.exceptions.ItemNotFoundException;
import com.asteway.inventory_management_service.services.InventoryService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@WebMvcTest(InventoryController.class)
public class InventoryControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    InventoryService inventoryService;

    @Test
    public void testGetItems() throws Exception {
        Mockito.when(inventoryService.getItems()).thenReturn(getSampleItemsDTO());

        mockMvc.perform(MockMvcRequestBuilders.get("/inventory"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2));
    }

    @Test
    public void testGetItemById() throws ItemNotFoundException, Exception {
        Mockito.when(inventoryService.getItemById("1")).thenReturn(getSampleItemsDTO().get(0));
        Mockito.when(inventoryService.getItemById("2")).thenReturn(getSampleItemsDTO().get(1));

        mockMvc.perform(MockMvcRequestBuilders.get("/inventory/{id}", 1))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Phone"));

        mockMvc.perform(MockMvcRequestBuilders.get("/inventory/{id}", 2))
               .andExpect(MockMvcResultMatchers.status().isOk())
               .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Book"));
    }

    @Test
    public void testAddItem() throws Exception {
        Mockito.when(inventoryService.addItem(getSampleItemsDTO().get(0))).thenReturn(getSampleItemsDTO().get(0));

        mockMvc.perform(MockMvcRequestBuilders.post("/inventory")
                       .contentType(MediaType.APPLICATION_JSON)
                                              .content(asJsonString(getSampleItemsDTO().get(0))))
               .andExpect(MockMvcResultMatchers.status().isOk())
               .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Phone"));
    }

    @Test
    public void testUpdateItem() throws ItemNotFoundException, Exception {
        ItemDTO itemDTO = getSampleItemsDTO().get(1);

        Mockito.when(inventoryService.updateItem("2", itemDTO)).thenReturn(itemDTO);

        mockMvc.perform(MockMvcRequestBuilders.put("/inventory/{id}", "2")
                                              .contentType(MediaType.APPLICATION_JSON)
                                              .content(asJsonString(itemDTO)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Book"));
    }

    @Test
    public void testdeleteById() throws ItemNotFoundException, Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/inventory/{id}", "1"))
               .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable String id) throws ItemNotFoundException {
        inventoryService.deleteItem(id);

        return ResponseEntity.noContent().build();
    }

    public String asJsonString(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.writeValueAsString(object);
    }

    public List<ItemDTO> getSampleItemsDTO(){
        return Arrays.asList(ItemDTO.builder().name("Phone").build(), ItemDTO.builder().name("Book").build());
    }
}
