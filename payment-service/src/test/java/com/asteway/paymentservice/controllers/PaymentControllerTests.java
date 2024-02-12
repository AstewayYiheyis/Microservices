package com.asteway.paymentservice.controllers;

import com.asteway.paymentservice.dtos.PaymentRequestDTO;
import com.asteway.paymentservice.dtos.PaymentResponseDTO;
import com.asteway.paymentservice.services.PaymentService;
import com.asteway.paymentservice.utils.PaymentUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@WebMvcTest(PaymentController.class)
public class PaymentControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PaymentService paymentService;

    @Test
    public void testProcessPayment() throws Exception {
        PaymentResponseDTO response = PaymentUtil.generatePaymentResponseDTO();
        PaymentRequestDTO request = PaymentUtil.generatePaymentRequestDTO();

        Mockito.when(paymentService.processPayment(request)).thenReturn(response);

        mockMvc.perform(MockMvcRequestBuilders.post("/process").contentType(MediaType.APPLICATION_JSON)
                                              .content(PaymentUtil.toJson(request)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.amount").value(10000));
    }
}
