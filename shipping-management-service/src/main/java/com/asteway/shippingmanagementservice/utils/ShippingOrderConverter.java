package com.asteway.shippingmanagementservice.utils;

import com.asteway.shippingmanagementservice.dtos.AddressDTO;
import com.asteway.shippingmanagementservice.dtos.ResponseDTO;
import com.asteway.shippingmanagementservice.dtos.ShipmentDTO;
import com.asteway.shippingmanagementservice.dtos.UserEntityDTO;
import com.asteway.shippingmanagementservice.entities.Address;
import com.asteway.shippingmanagementservice.entities.Shipment;
import com.asteway.shippingmanagementservice.entities.UserEntity;
import org.springframework.beans.BeanUtils;

public class ShippingOrderConverter {
    public static ShipmentDTO getShipmentDTO(Shipment shipment) {
        ShipmentDTO shipmentDTO = new ShipmentDTO();

        BeanUtils.copyProperties(shipment, shipmentDTO);

        shipmentDTO.setSender(getDTOFromUser(shipment.getSender()));
        shipmentDTO.setReceiver(getDTOFromUser(shipment.getReceiver()));
        shipmentDTO.setShippingAddress(getDTOFromAddress(shipment.getShippingAddress()));

        return shipmentDTO;
    }

    public static ResponseDTO getShipmentResponseDTO(Shipment shipment) {
        ResponseDTO responseDTO = new ResponseDTO();
        ShipmentDTO shipmentDTO = getShipmentDTO(shipment);

        BeanUtils.copyProperties(shipmentDTO, responseDTO);
        responseDTO.setTrackingNumber(shipment.getTrackingNumber());

        return responseDTO;
    }

    public static Shipment getShipmentFromDTO(ShipmentDTO shipmentDTO) {
        Shipment shipment = new Shipment();

        BeanUtils.copyProperties(shipmentDTO, shipment);

        shipment.setSender(getUserFromDTO(shipmentDTO.getSender()));
        shipment.setReceiver(getUserFromDTO(shipmentDTO.getReceiver()));
        shipment.setShippingAddress(getAddressFromDTO(shipmentDTO.getShippingAddress()));
        shipment.setDefaultTrackingNumber();

        return shipment;
    }

    public static UserEntity getUserFromDTO(UserEntityDTO userEntityDTO){
        return UserEntity.builder()
                         .email(userEntityDTO.getEmail())
                         .phoneNumber(userEntityDTO.getPhoneNumber())
                         .address(getAddressFromDTO(userEntityDTO.getAddress()))
                         .firstName(userEntityDTO.getFirstName())
                         .lastName(userEntityDTO.getLastName())
                         .build();
    }

    public static UserEntityDTO getDTOFromUser(UserEntity userEntity){
        return UserEntityDTO.builder()
                         .email(userEntity.getEmail())
                         .phoneNumber(userEntity.getPhoneNumber())
                         .address(getDTOFromAddress(userEntity.getAddress()))
                         .firstName(userEntity.getFirstName())
                         .lastName(userEntity.getLastName())
                         .build();
    }

    public static Address getAddressFromDTO(AddressDTO addressDTO){
        return Address.builder()
                      .street(addressDTO.getStreet())
                      .city(addressDTO.getCity())
                      .country(addressDTO.getCountry())
                      .postalCode(addressDTO.getPostalCode())
                      .state(addressDTO.getState())
                      .build();
    }

    public static AddressDTO getDTOFromAddress(Address address){
        return AddressDTO.builder()
                      .street(address.getStreet())
                      .city(address.getCity())
                      .country(address.getCountry())
                      .postalCode(address.getPostalCode())
                      .state(address.getState())
                      .build();
    }
}
