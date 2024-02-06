package com.asteway.shippingmanagementservice.commons;

import com.asteway.shippingmanagementservice.dtos.AddressDTO;
import com.asteway.shippingmanagementservice.dtos.ResponseDTO;
import com.asteway.shippingmanagementservice.dtos.ShipmentDTO;
import com.asteway.shippingmanagementservice.dtos.UserEntityDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Arrays;
import java.util.List;

public class SampleData {
    public static List<ShipmentDTO> getSampleShipmentDTOs() {
        List<UserEntityDTO> userEntityDTOS = getSampleUserDTOs();

        List<ShipmentDTO> shipmentDTOS = Arrays.asList(ShipmentDTO.builder().sender(userEntityDTOS.get(0))
                                                                  .receiver(userEntityDTOS.get(1))
                                                                  .shippingAddress(AddressDTO.builder().state("Virginia").build()).build(),
                ShipmentDTO.builder().sender(userEntityDTOS.get(2))
                           .receiver(userEntityDTOS.get(3)).shippingAddress(AddressDTO.builder().state("Virginia").build()).build());

        return shipmentDTOS;
    }

    public static List<ResponseDTO> getResponseDTOs() {
        List<UserEntityDTO> userEntityDTOS = getSampleUserDTOs();

        List<ResponseDTO> responseDTOS = Arrays.asList(ResponseDTO.builder().sender(userEntityDTOS.get(0))
                                                                  .receiver(userEntityDTOS.get(1)).trackingNumber("1").build(),
                ResponseDTO.builder().sender(userEntityDTOS.get(2))
                           .receiver(userEntityDTOS.get(3)).trackingNumber("2").build());

        return responseDTOS;
    }

    public static List<UserEntityDTO> getSampleUserDTOs() {
        List<UserEntityDTO> userEntityDTOS = Arrays.asList(UserEntityDTO.builder().firstName("CustomerOne")
                                                                        .address(AddressDTO.builder()
                                                                                           .state("Virginia").build()).build(),
                UserEntityDTO.builder().firstName("CustomerTwo").address(AddressDTO.builder().state("Virginia").build()).build(),
                UserEntityDTO.builder().firstName("CustomerThree").address(AddressDTO.builder().state("Texas").build()).build(),
                UserEntityDTO.builder().firstName("CustomerFour").address(AddressDTO.builder().state("Tennessee").build()).build());

        return userEntityDTOS;
    }

    public static String asJsonString(final Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.writeValueAsString(obj);
    }
}
