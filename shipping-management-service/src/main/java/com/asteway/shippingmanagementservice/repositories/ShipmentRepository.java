package com.asteway.shippingmanagementservice.repositories;

import com.asteway.shippingmanagementservice.entities.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ShipmentRepository extends JpaRepository<Shipment, Long> {
    @Query("select s from Shipment s where s.trackingNumber =?1")
    Shipment getShipmentByTrackingNumber(String trackingNumber);
}
