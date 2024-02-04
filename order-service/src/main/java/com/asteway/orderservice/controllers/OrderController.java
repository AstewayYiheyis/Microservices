package com.asteway.orderservice.controllers;

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
    public ResponseEntity<List<OrderEntity>> getAllOrders(){
        return new ResponseEntity<>(orderService.getAllOrders(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderEntity> getOrderById(@PathVariable Long id){
        return new ResponseEntity<>(orderService.getOrderById(id), HttpStatus.OK);
    }

    @GetMapping("/{id}/items")
    public ResponseEntity<List<Item>> getItemsByOrderId(@PathVariable Long id){
        return new ResponseEntity<>(orderService.getItemsByOrderId(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<OrderEntity> createOrder(@RequestBody OrderEntity order) throws EmptyItemsException {
        return new ResponseEntity<>(orderService.createOrder(order), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id){
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/tracking/{trackingNumber}")
    public ResponseEntity<OrderEntity> getOrderByTrackingNumber(@PathVariable String trackingNumber) {
        return new ResponseEntity<>(orderService.getOrderByTrackingNumber(trackingNumber), HttpStatus.OK);
    }
}
