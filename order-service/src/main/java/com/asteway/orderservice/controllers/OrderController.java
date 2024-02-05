package com.asteway.orderservice.controllers;

import com.asteway.orderservice.dtos.ItemDTO;
import com.asteway.orderservice.dtos.OrderDTO;
import com.asteway.orderservice.entities.Item;
import com.asteway.orderservice.entities.OrderEntity;
import com.asteway.orderservice.exceptions.EmptyItemsException;
import com.asteway.orderservice.services.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private OrderService orderService;

    public OrderController(final OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<List<OrderDTO>> getAllOrders(){
        return new ResponseEntity<>(orderService.getAllOrders(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable Long id){
        return new ResponseEntity<>(orderService.getOrderById(id), HttpStatus.OK);
    }

    @GetMapping("/{id}/items")
    public ResponseEntity<List<ItemDTO>> getItemsByOrderId(@PathVariable Long id){
        return new ResponseEntity<>(orderService.getItemsByOrderId(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderDTO orderDTO) throws EmptyItemsException {
        return new ResponseEntity<>(orderService.createOrder(orderDTO), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id){
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/tracking/{trackingNumber}")
    public ResponseEntity<OrderDTO> getOrderByTrackingNumber(@PathVariable String trackingNumber) {
        return new ResponseEntity<>(orderService.getOrderByTrackingNumber(trackingNumber), HttpStatus.OK);
    }
}
