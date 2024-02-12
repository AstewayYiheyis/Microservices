package com.asteway.paymentservice.services;

import com.asteway.paymentservice.dtos.PaymentRequestDTO;
import com.asteway.paymentservice.repositories.PaymentRepository;
import com.asteway.paymentservice.utils.PaymentUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@RunWith(SpringRunner.class)
@WebMvcTest(PaymentService.class)
public class PaymentServiceTests {
    @Autowired
    private PaymentService paymentService;

    @MockBean
    private PaymentRepository paymentRepository;

    @MockBean
    private InvoiceService invoiceService;

    @Test
    public void testValidatePaymentRequest(){
        PaymentRequestDTO request = PaymentUtil.generatePaymentRequestDTO();

        assertDoesNotThrow(() -> paymentService.validatePaymentRequest(request));
    }
}
