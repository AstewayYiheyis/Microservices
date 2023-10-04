package com.asteway_project.pricing_service.entities;

import com.asteway_project.pricing_service.common_classes.ShippingType;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
@AllArgsConstructor
public class PackageDetail {
    public double weight;
    public ShippingType shippingtype;
}