package com.asteway.paymentservice.utils;

import com.asteway.paymentservice.dtos.PaymentRequestDTO;
import com.asteway.paymentservice.dtos.PaymentResponseDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.math.BigDecimal;

public class PaymentUtil {
    public static PaymentResponseDTO generatePaymentResponseDTO(){
        return PaymentResponseDTO.builder().paymentId(1L).amount(new BigDecimal(10000)).status("PAID").build();
    }

    public static PaymentRequestDTO generatePaymentRequestDTO() {
        return PaymentRequestDTO.builder().amount(new BigDecimal(10000)).build();
    }

    public static String toJson(PaymentRequestDTO request) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.writeValueAsString(request);
    }
}
