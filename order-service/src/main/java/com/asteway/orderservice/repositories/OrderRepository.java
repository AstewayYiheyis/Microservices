package com.asteway.orderservice.repositories;

import com.asteway.orderservice.entities.Item;
import com.asteway.orderservice.entities.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    List<Item> getItemsByOrderId(Long orderId);

    @Query("select o from OrderEntity o where o.shippingDetails.trackingNumber =?1")
    OrderEntity getOrderByTrackingNumber(String trackingNumber);
}
