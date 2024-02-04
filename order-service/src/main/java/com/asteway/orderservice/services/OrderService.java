package com.asteway.orderservice.services;

import com.asteway.orderservice.entities.Item;
import com.asteway.orderservice.entities.OrderEntity;
import com.asteway.orderservice.exceptions.EmptyItemsException;
import com.asteway.orderservice.exceptions.OrderNotFoundException;
import com.asteway.orderservice.repositories.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    private OrderRepository orderRepository;

    OrderService(final OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }

    public List<OrderEntity> getAllOrders(){
        return orderRepository.findAll();
    }

    public OrderEntity getOrderById(Long id){
        return orderRepository.findById(id).get();
    }

    public OrderEntity createOrder(OrderEntity order) throws EmptyItemsException{
        if(order.getItems() != null){
            for(Item item : order.getItems()){
                item.setOrder(order);
            }
        }
        else{
            throw new EmptyItemsException("Order created with no items!");
        }

        return orderRepository.save(order);
    }

    public void deleteOrder(Long id){
        orderRepository.deleteById(id);
    }

    public List<Item> getItemsByOrderId(Long id) {
        return orderRepository.getItemsByOrderId(id);
    }

    public OrderEntity getOrderByTrackingNumber(String trackingNumber){
        OrderEntity order = orderRepository.getOrderByTrackingNumber(trackingNumber);

        if (order != null) {
            return order;
        } else {
            throw new OrderNotFoundException("Order not found for tracking number: " + trackingNumber);
        }
    }
}
