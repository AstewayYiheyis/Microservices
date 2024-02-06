package com.asteway.shippingmanagementservice.utils;

import java.util.UUID;

public class CommonUtils {
    public static String generateTrackingNumber() {
        return UUID.randomUUID().toString().replace("-", "").substring(0, 12);
    }
}
