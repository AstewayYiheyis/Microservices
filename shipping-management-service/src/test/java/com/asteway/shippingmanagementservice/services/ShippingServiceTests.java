package com.asteway.shippingmanagementservice.services;

import com.asteway.shippingmanagementservice.dtos.ShipmentDTO;
import com.asteway.shippingmanagementservice.entities.Shipment;
import com.asteway.shippingmanagementservice.repositories.ShipmentRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.asteway.shippingmanagementservice.commons.SampleData.getSampleShipmentDTOs;
import static com.asteway.shippingmanagementservice.utils.ShippingOrderConverter.getShipmentFromDTO;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@WebMvcTest(ShipmentService.class)
public class ShippingServiceTests {
    @Autowired
    private ShipmentService shipmentService;
    @MockBean
    private ShipmentRepository shipmentRepository;

    @Test
    public void testGetAllShipments() {
        List<Shipment> shipments = new ArrayList<>();
        for(ShipmentDTO shipment:getSampleShipmentDTOs()){
            shipments.add(getShipmentFromDTO(shipment));
        }
        Mockito.when(shipmentRepository.findAll()).thenReturn(shipments);

        assertEquals(2, shipmentService.getAllShipments().size());
    }

    @Test
    public void testGetShipmentById() {
        List<Shipment> shipments = new ArrayList<>();

        for(ShipmentDTO shipmentDTO : getSampleShipmentDTOs()){
            shipments.add(getShipmentFromDTO(shipmentDTO));
        }

        Mockito.when(shipmentRepository.findById(2L)).thenReturn(Optional.of(shipments.get(1)));
        Mockito.when(shipmentRepository.findById(1L)).thenReturn(Optional.of(shipments.get(0)));

        assertEquals("CustomerTwo", shipmentService.getShipmentById(1L).getReceiver().getFirstName());
        assertEquals("CustomerFour", shipmentService.getShipmentById(2L).getReceiver().getFirstName());
    }

//    @Test
//    public void testCreateShipment() {
//        ShipmentDTO shipmentDTO = getSampleShipmentDTOs().get(0);
//        Shipment shipment = getShipmentFromDTO(shipmentDTO);
//
//        Mockito.when(shipmentRepository.save(shipment)).thenReturn(shipment);
//
//        assertEquals("", shipmentService.createShipment(shipmentDTO));
//    }

    @Test
    public void testDeleteOrder() {
        Long shipmentId = 1L;

        Mockito.doNothing().when(shipmentRepository).deleteById(shipmentId);
        shipmentService.deleteShipment(shipmentId);

        Mockito.verify(shipmentRepository, Mockito.times(1)).deleteById(shipmentId);
    }
}
