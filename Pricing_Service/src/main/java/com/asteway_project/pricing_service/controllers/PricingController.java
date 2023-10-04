package com.asteway_project.pricing_service.controllers;

import com.asteway_project.pricing_service.entities.PackageDetail;
import com.asteway_project.pricing_service.entities.PricingInfo;
import com.asteway_project.pricing_service.services.PricingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shipping")
public class PricingController {
    PricingService pricingService;

    @Autowired
    PricingController(final PricingService pricingService){
        this.pricingService = pricingService;
    }

    @PostMapping("/pricing")
    public ResponseEntity<PricingInfo> shipPackage(@RequestBody PackageDetail packageDetail){
        return new ResponseEntity(pricingService.getPricingInfo(packageDetail), HttpStatusCode.valueOf(200));
    }
}
