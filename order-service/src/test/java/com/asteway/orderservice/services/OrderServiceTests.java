package com.asteway.orderservice.services;

import com.asteway.orderservice.entities.Item;
import com.asteway.orderservice.entities.OrderEntity;
import com.asteway.orderservice.repositories.OrderRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@WebMvcTest(OrderService.class)
public class OrderServiceTests {
    @Autowired
    private OrderService orderService;
    @MockBean
    private OrderRepository orderRepository;

    @Test
    public void testGetAllOrders() {
        // mock data
        List<OrderEntity> orderEntities = List.of(new OrderEntity(),
                new OrderEntity(),
                new OrderEntity());

        // mock the repository behavior
        Mockito.when(orderRepository.findAll()).thenReturn(orderEntities);

        assertEquals(3, orderService.getAllOrders().size());
    }

    @Test
    public void testGetOrderById() {
        List<OrderEntity> orderEntities = List.of(OrderEntity.builder().orderId(1L).customerName("CustomerOne").build(),
                OrderEntity.builder().orderId(2L).customerName("CustomerTwo").build(),
                OrderEntity.builder().orderId(3L).customerName("CustomerThree").build());

        Mockito.when(orderRepository.findById(2L)).thenReturn(Optional.of(orderEntities.get(1)));
        Mockito.when(orderRepository.findById(1L)).thenReturn(Optional.of(orderEntities.get(0)));

        assertEquals("CustomerOne", orderService.getOrderById(1L).getCustomerName());
        assertEquals("CustomerTwo", orderService.getOrderById(2L).getCustomerName());
    }

    @Test
    public void testTestItemsByOrderId() {
        List<Item> items = Arrays.asList(Item.builder().itemName("Book").quantity(10).build(),
                Item.builder().itemName("Phone").quantity(1).build());
        OrderEntity orderEntity = OrderEntity.builder().items(items).build();
        List<Item> itemsTwo = Arrays.asList(Item.builder().itemName("Book").quantity(10).build());

        Mockito.when(orderRepository.findById(1L)).thenReturn(Optional.of(orderEntity));
        Mockito.when(orderRepository.getItemsByOrderId(1L)).thenReturn(items);
        Mockito.when(orderRepository.findById(2L)).thenReturn(Optional.of(orderEntity));
        Mockito.when(orderRepository.getItemsByOrderId(2L)).thenReturn(itemsTwo);

        assertEquals(2, orderService.getItemsByOrderId(1L).size());
        assertEquals(1, orderService.getItemsByOrderId(2L).size());
        assertEquals("Phone", orderService.getItemsByOrderId(1L).get(1).getItemName());
    }

    @Test
    public void testDeleteOrder() {
        Long orderId = 1L;

        Mockito.doNothing().when(orderRepository).deleteById(orderId);
        orderService.deleteOrder(orderId);

        Mockito.verify(orderRepository, Mockito.times(1)).deleteById(orderId);
    }
}
