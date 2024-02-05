package com.asteway.orderservice.controllers;

import com.asteway.orderservice.dtos.OrderDTO;
import com.asteway.orderservice.entities.OrderEntity;
import com.asteway.orderservice.exceptions.EmptyItemsException;
import com.asteway.orderservice.services.OrderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@WebMvcTest(OrderController.class)
public class OrderControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService orderService;

    @Test
    public void testGetAllOrders() throws Exception{
        Mockito.when(orderService.getAllOrders()).thenReturn(getSampleOrderDTOs());

        mockMvc.perform(MockMvcRequestBuilders.get("/orders"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2));
    }

    @Test
    public void testGetOrderById() throws Exception {
        Mockito.when(orderService.getOrderById(1L)).thenReturn(getSampleOrderDTOs().get(0));
        Mockito.when(orderService.getOrderById(2L)).thenReturn(getSampleOrderDTOs().get(1));

        mockMvc.perform(MockMvcRequestBuilders.get("/orders/{id}", 1))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.customerName").value("CustomerOne"));

        mockMvc.perform(MockMvcRequestBuilders.get("/orders/{id}", 2))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.customerName").value("CustomerTwo"));
    }

    @Test
    public void testCreateOrder() throws Exception, EmptyItemsException {
        OrderDTO orderDTO = OrderDTO.builder().customerName("John Doe").build();

        Mockito.when(orderService.createOrder(orderDTO)).thenReturn(orderDTO);

        mockMvc.perform(MockMvcRequestBuilders.post("/orders")
                                              .contentType(MediaType.APPLICATION_JSON)
                                              .content(asJsonString(orderDTO)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.customerName").value("John Doe"));
    }

    public List<OrderDTO> getSampleOrderDTOs(){
        OrderDTO orderDTO1 = OrderDTO.builder().customerName("CustomerOne").build();
        OrderDTO orderDTO2 = OrderDTO.builder().customerName("CustomerTwo").build();

        List<OrderDTO> orderDTOs= Arrays.asList(orderDTO1,
                orderDTO2);

        return orderDTOs;
    }

    public String asJsonString(final Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.writeValueAsString(obj);
    }
}
