package com.asteway.paymentservice.services;

import com.asteway.paymentservice.entities.PaymentEntity;
import com.asteway.paymentservice.repositories.PaymentRepository;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

import static org.apache.pdfbox.pdmodel.font.Standard14Fonts.FontName.HELVETICA;
import static org.apache.pdfbox.pdmodel.font.Standard14Fonts.FontName.HELVETICA_BOLD;

@Service
public class InvoiceServiceImpl implements InvoiceService {
    private PaymentRepository paymentRepository;

    public InvoiceServiceImpl(final PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public void generateInvoice(Long paymentId) throws IOException {
        PaymentEntity paymentEntity = paymentRepository.findById(paymentId)
                                                       .orElseThrow(() -> new IllegalArgumentException("Payment not " +
                                                               "found for payment id: " + paymentId));
        // generate invoice content
        try(PDDocument document = new PDDocument()){
            PDPage page = new PDPage();
            document.addPage(page);

            try(PDPageContentStream contentStream = new PDPageContentStream(document, page)){
                contentStream.beginText();
                contentStream.setFont(new PDType1Font(HELVETICA_BOLD), 12);
                contentStream.newLineAtOffset(100, 700);

                // Write invoice details
                contentStream.showText("Invoice for Payment ID: " + paymentId);
                contentStream.newLine();
                contentStream.newLine();
                contentStream.setFont(new PDType1Font(HELVETICA), 10);
               // contentStream.showText("Item 1: $50");
                contentStream.showText(String.valueOf(paymentEntity.getTimestamp()));
                contentStream.showText(paymentEntity.getStatus());
                //contentStream.showText("Item 2: $30");
                contentStream.newLine();
                contentStream.showText("Total Amount: " + paymentEntity.getAmount());

                contentStream.endText();
            }

            // Save the PDF document
            String fileName = "invoice_" + paymentId + ".pdf";
            document.save(new File(fileName));
        }
    }
}
