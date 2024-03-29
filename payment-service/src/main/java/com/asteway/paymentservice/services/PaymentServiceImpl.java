package com.asteway.paymentservice.services;

import com.asteway.paymentservice.dtos.PaymentRequestDTO;
import com.asteway.paymentservice.dtos.PaymentResponseDTO;
import com.asteway.paymentservice.entities.PaymentEntity;
import com.asteway.paymentservice.repositories.PaymentRepository;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Service
public class PaymentServiceImpl implements PaymentService{
    @Value("${stripe.api.key}")
    private String stripeApiKey;

    private PaymentRepository paymentRepository;
    private InvoiceService invoiceService;

    public PaymentServiceImpl(PaymentRepository paymentRepository, InvoiceService invoiceService) {
        this.paymentRepository = paymentRepository;
        this.invoiceService = invoiceService;
    }

    @Override
    public PaymentResponseDTO processPayment(PaymentRequestDTO request) {
        validatePaymentRequest(request);

        // Process payment using Stripe
        Stripe.apiKey = stripeApiKey;

        BigDecimal amount = request.getAmount();
        Map<String, Object> chargeParams = new HashMap<>();
        chargeParams.put("amount", amount.multiply(BigDecimal.valueOf(100)).intValue()); // Stripe expects amount in cents
        chargeParams.put("currency", "usd");
        chargeParams.put("source", request.getStripeToken()); // Stripe token obtained from client-side

        try {
            Charge charge = Charge.create(chargeParams);
            // Check if payment was successful
            if (charge.getStatus().equals("succeeded")) {
                // Payment was successful, save payment details to database
                PaymentEntity paymentEntity = new PaymentEntity();
                paymentEntity.setAmount(amount);
                paymentEntity.setStatus("PAID"); // Update status based on charge status if needed
                paymentRepository.save(paymentEntity);

                // Generate payment response
                PaymentResponseDTO responseDTO = new PaymentResponseDTO();
                responseDTO.setPaymentId(paymentEntity.getId());
                responseDTO.setAmount(paymentEntity.getAmount());
                responseDTO.setStatus(paymentEntity.getStatus());
                invoiceService.generateInvoice(paymentEntity.getId());

                return responseDTO;
            } else {
                // Payment failed or was not completed
                throw new RuntimeException("Payment was not successful. Status: " + charge.getStatus());
            }
        } catch (StripeException | IOException e) {
            // Handle Stripe processing errors
            throw new RuntimeException("Error processing payment: " + e.getMessage());
        }
    }

    public void validatePaymentRequest(PaymentRequestDTO paymentRequestDTO) {
        if(paymentRequestDTO.getAmount().compareTo(BigDecimal.ZERO) <= 0){
            throw new IllegalArgumentException("Payment amount must be greater than zero.");
        }
    }
}
