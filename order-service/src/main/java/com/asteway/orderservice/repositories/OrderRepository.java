package com.asteway.orderservice.repositories;

import com.asteway.orderservice.entities.Item;
import com.asteway.orderservice.entities.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    @Query("select i from Item i where i.order.orderId = :orderId")
    List<Item> getItemsByOrderId(@Param("orderId") Long orderId);

    @Query("select o from OrderEntity o where o.shippingDetails.trackingNumber =?1")
    OrderEntity getOrderByTrackingNumber(String trackingNumber);
}
