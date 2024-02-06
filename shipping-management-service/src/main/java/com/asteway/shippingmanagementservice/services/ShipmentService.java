package com.asteway.shippingmanagementservice.services;

import com.asteway.shippingmanagementservice.dtos.ResponseDTO;
import com.asteway.shippingmanagementservice.dtos.ShipmentDTO;
import com.asteway.shippingmanagementservice.entities.Shipment;
import com.asteway.shippingmanagementservice.exceptions.OrderNotFoundException;
import com.asteway.shippingmanagementservice.repositories.ShipmentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.asteway.shippingmanagementservice.utils.ShippingOrderConverter.*;

@Service
public class ShipmentService {
    private ShipmentRepository shipmentRepository;

    ShipmentService(final ShipmentRepository shipmentRepository){
        this.shipmentRepository = shipmentRepository;
    }

    public List<ResponseDTO> getAllShipments(){
        final List<ResponseDTO> responseDTOS = new ArrayList<>();

        for(Shipment shipment:shipmentRepository.findAll()){
            responseDTOS.add(getShipmentResponseDTO(shipment));
        }

        return responseDTOS;
    }

    public ResponseDTO getShipmentById(Long id){
        if(!orderIsPresent(id)){
            throw new OrderNotFoundException("There is no Order with the provided ID!");
        }

        return getShipmentResponseDTO(shipmentRepository.findById(id).get());
    }

    public boolean orderIsPresent(Long id){
        return shipmentRepository.findById(id).isPresent();
    }

    public ResponseDTO createShipment(ShipmentDTO shipmentDTO){
        Shipment shipment = getShipmentFromDTO(shipmentDTO);

        return getShipmentResponseDTO(shipmentRepository.save(shipment));
    }

    public void deleteShipment(Long id){
        shipmentRepository.deleteById(id);
    }

    public ResponseDTO getShipmentByTrackingNumber(String trackingNumber){
        Shipment shipment = shipmentRepository.getShipmentByTrackingNumber(trackingNumber);

        if (shipment != null) {
            return getShipmentResponseDTO(shipment);
        } else {
            throw new OrderNotFoundException("Shipping Order not found for tracking number: " + trackingNumber);
        }
    }
}
