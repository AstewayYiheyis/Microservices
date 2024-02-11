package com.asteway.paymentservice.services;

import java.io.IOException;

public interface InvoiceService {
    void generateInvoice(Long paymentId) throws IOException;
}
