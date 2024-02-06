package com.asteway.shippingmanagementservice.entities;

import com.asteway.shippingmanagementservice.utils.CommonUtils;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Shipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "sender_id", nullable = false)
    private UserEntity sender;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "receiver_id", nullable = false)
    private UserEntity receiver;

    @Embedded
    private Address shippingAddress;
    private String trackingNumber;

    public void setDefaultTrackingNumber(){
        this.trackingNumber = CommonUtils.generateTrackingNumber();
    }
}
