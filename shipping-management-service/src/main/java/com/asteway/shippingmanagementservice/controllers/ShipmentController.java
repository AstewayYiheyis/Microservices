package com.asteway.shippingmanagementservice.controllers;

import com.asteway.shippingmanagementservice.dtos.ResponseDTO;
import com.asteway.shippingmanagementservice.dtos.ShipmentDTO;
import com.asteway.shippingmanagementservice.services.ShipmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shipment")
public class ShipmentController {
    private ShipmentService shippingOrderService;

    public ShipmentController(final ShipmentService shipmentService) {
        this.shippingOrderService = shipmentService;
    }

    @GetMapping
    public ResponseEntity<List<ResponseDTO>> getAllShipments(){
        return new ResponseEntity<>(shippingOrderService.getAllShipments(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> getShipmentById(@PathVariable Long id){
        return new ResponseEntity<>(shippingOrderService.getShipmentById(id), HttpStatus.OK);
    }

    @GetMapping("/tracking/{trackingNumber}")
    public ResponseEntity<ResponseDTO> getShipmentByTrackingNumber(@PathVariable String trackingNumber){
        return new ResponseEntity<>(shippingOrderService.getShipmentByTrackingNumber(trackingNumber), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ResponseDTO> createShipment(@RequestBody ShipmentDTO shipmentDTO) {
        return new ResponseEntity<>(shippingOrderService.createShipment(shipmentDTO), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteShipment(@PathVariable Long id){
        shippingOrderService.deleteShipment(id);
        return ResponseEntity.noContent().build();
    }
}
