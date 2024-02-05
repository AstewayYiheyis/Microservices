package com.asteway.orderservice.services;

import com.asteway.orderservice.dtos.ItemDTO;
import com.asteway.orderservice.dtos.OrderDTO;
import com.asteway.orderservice.entities.Item;
import com.asteway.orderservice.entities.OrderEntity;
import com.asteway.orderservice.exceptions.EmptyItemsException;
import com.asteway.orderservice.exceptions.OrderNotFoundException;
import com.asteway.orderservice.repositories.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.asteway.orderservice.utils.OrderConverter.*;

@Service
public class OrderService {
    private OrderRepository orderRepository;

    OrderService(final OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }

    public List<OrderDTO> getAllOrders(){
        final List<OrderDTO> orderDTOs = new ArrayList<>();

        for(OrderEntity orderEntity:orderRepository.findAll()){
            orderDTOs.add(getOrderDTO(orderEntity));
        }

        return orderDTOs;
    }

    public OrderDTO getOrderById(Long id){
        if(!orderIsPresent(id)){
            throw new OrderNotFoundException("There is no Order with the provided ID!");
        }

        return getOrderDTO(orderRepository.findById(id).get());
    }

    public boolean orderIsPresent(Long id){
        return orderRepository.findById(id).isPresent();
    }

    public OrderDTO createOrder(OrderDTO orderDTO) throws EmptyItemsException{
        OrderEntity orderEntity = getOrderFromDTO(orderDTO);

        if(orderDTO.getItems() != null){
            for(Item item : orderDTO.getItems()){
                item.setOrder(orderEntity);
            }
        }
        else{
            throw new EmptyItemsException("Order created with no items!");
        }

        return getOrderDTO(orderRepository.save(orderEntity));
    }

    public void deleteOrder(Long id){
        orderRepository.deleteById(id);
    }

    public List<ItemDTO> getItemsByOrderId(Long id) {
        final List<ItemDTO> itemDTOs = new ArrayList<>();

        if(orderIsPresent(id)) {
            for(Item item:orderRepository.getItemsByOrderId(id)){
                itemDTOs.add(getItemDTO(item));
            }
            return itemDTOs;
        }
        else{
            throw new OrderNotFoundException("Order not found for id: " + id);
        }
    }

    public OrderDTO getOrderByTrackingNumber(String trackingNumber){
        OrderEntity order = orderRepository.getOrderByTrackingNumber(trackingNumber);

        if (order != null) {
            return getOrderDTO(order);
        } else {
            throw new OrderNotFoundException("Order not found for tracking number: " + trackingNumber);
        }
    }
}
