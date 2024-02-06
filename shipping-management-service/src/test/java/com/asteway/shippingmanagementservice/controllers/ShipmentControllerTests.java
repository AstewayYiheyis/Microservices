package com.asteway.shippingmanagementservice.controllers;

import com.asteway.shippingmanagementservice.dtos.ResponseDTO;
import com.asteway.shippingmanagementservice.dtos.ShipmentDTO;
import com.asteway.shippingmanagementservice.dtos.UserEntityDTO;
import com.asteway.shippingmanagementservice.services.ShipmentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static com.asteway.shippingmanagementservice.commons.SampleData.*;

@RunWith(SpringRunner.class)
@WebMvcTest(ShipmentController.class)
public class ShipmentControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ShipmentService shipmentService;

    private final String URL = "/shipment";

    @Test
    public void testGetAllShipments() throws Exception{
        Mockito.when(shipmentService.getAllShipments()).thenReturn(getResponseDTOs());

        mockMvc.perform(MockMvcRequestBuilders.get(URL))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2));
    }

    @Test
    public void testGetShipmentById() throws Exception {
        Mockito.when(shipmentService.getShipmentById(1L)).thenReturn(getResponseDTOs().get(0));
        Mockito.when(shipmentService.getShipmentById(2L)).thenReturn(getResponseDTOs().get(1));

        mockMvc.perform(MockMvcRequestBuilders.get(URL + "/{id}", 1))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.trackingNumber").value("1"));

        mockMvc.perform(MockMvcRequestBuilders.get( URL + "/{id}", 2))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.trackingNumber").value("2"));
    }

    @Test
    public void testCreateShipment() throws Exception{
        List<UserEntityDTO> userEntityDTO = getSampleUserDTOs();
        ShipmentDTO shipmentDTO = ShipmentDTO.builder().sender(userEntityDTO.get(1))
                                             .receiver(userEntityDTO.get(0)).build();
        ResponseDTO responseDTO = new ResponseDTO();

        BeanUtils.copyProperties(shipmentDTO, responseDTO);
        responseDTO.setTrackingNumber("2");

        Mockito.when(shipmentService.createShipment(shipmentDTO)).thenReturn(responseDTO);

        mockMvc.perform(MockMvcRequestBuilders.post(URL)
                                              .contentType(MediaType.APPLICATION_JSON)
                                              .content(asJsonString(shipmentDTO)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.trackingNumber").value("2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.sender.firstName").value("CustomerTwo"));
    }
}
