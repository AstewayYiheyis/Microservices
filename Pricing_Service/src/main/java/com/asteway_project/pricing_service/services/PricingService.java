package com.asteway_project.pricing_service.services;

import com.asteway_project.pricing_service.entities.PackageDetail;
import com.asteway_project.pricing_service.entities.PricingInfo;
import org.springframework.stereotype.Service;

@Service
public class PricingService {
    public PricingInfo getPricingInfo(PackageDetail packageDetail) {
        double price = 0;

        switch(packageDetail.shippingtype){
            case STANDARD:
                price = 10  * packageDetail.weight;
                break;
            case FRAGILE:
                price =  20 * packageDetail.weight;
                break;
            case FIRST_CLASS:
                price = 30 * packageDetail.weight;
                break;
        }

        return PricingInfo.builder().price(price).build();
    }
}
