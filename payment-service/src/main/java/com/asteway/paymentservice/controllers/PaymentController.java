package com.asteway.paymentservice.controllers;

import com.asteway.paymentservice.dtos.PaymentRequestDTO;
import com.asteway.paymentservice.dtos.PaymentResponseDTO;
import com.asteway.paymentservice.services.PaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {
    private PaymentService paymentService;

    public PaymentController(final PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/process")
    public ResponseEntity<PaymentResponseDTO> processPayment(@RequestBody PaymentRequestDTO request) {
        return new ResponseEntity<>(paymentService.processPayment(request), HttpStatus.OK);
    }
}
