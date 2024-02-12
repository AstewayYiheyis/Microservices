package com.asteway.paymentservice.services;

import com.asteway.paymentservice.dtos.PaymentRequestDTO;
import com.asteway.paymentservice.dtos.PaymentResponseDTO;

public interface PaymentService {
    PaymentResponseDTO processPayment(PaymentRequestDTO paymentRequestDTO);
    void validatePaymentRequest(PaymentRequestDTO paymentRequestDTO);
}
