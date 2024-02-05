package com.asteway.orderservice.utils;

import com.asteway.orderservice.dtos.ItemDTO;
import com.asteway.orderservice.dtos.OrderDTO;
import com.asteway.orderservice.entities.Item;
import com.asteway.orderservice.entities.OrderEntity;
import jakarta.persistence.criteria.Order;
import org.springframework.beans.BeanUtils;

public class OrderConverter {
    public static OrderDTO getOrderDTO(OrderEntity order){
        OrderDTO orderDTO = new OrderDTO();

        BeanUtils.copyProperties(order, orderDTO);

        return orderDTO;
    }

    public static OrderEntity getOrderFromDTO(OrderDTO orderDTO){
        OrderEntity orderEntity = new OrderEntity();

        BeanUtils.copyProperties(orderDTO, orderEntity);

        return orderEntity;
    }

    public static ItemDTO getItemDTO(Item item){
        ItemDTO itemDTO = new ItemDTO();

        BeanUtils.copyProperties(item, itemDTO);

        return itemDTO;
    }

    public static Item getItemFromDTO(ItemDTO itemDTO){
        Item item = new Item();

        BeanUtils.copyProperties(itemDTO, item);

        return item;
    }
}
