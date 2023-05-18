package org.geekhub.vitalii.service;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import org.geekhub.vitalii.dto.CustomerStatsDTO;
import org.geekhub.vitalii.repository.PDFRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;

@Service
public class PDFService {

    private final PDFRepository pdfRepository;

    @Autowired
    public PDFService(PDFRepository pdfRepository) {
        this.pdfRepository = pdfRepository;
    }

    public void getStatsTable(HttpServletResponse response, String username) {
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition",
            "attachment; filename=portfolioStats" + LocalDate.now() + ".pdf");

        CustomerStatsDTO customerStats = pdfRepository.getCustomerStats(username);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(outputStream);
        PdfDocument pdfDoc = new PdfDocument(writer);
        Document doc = new Document(pdfDoc);

        Table table = new Table(5);

        table.addCell(new Cell().add(new Paragraph("Cryptocurrency value")));
        table.addCell(new Cell().add(new Paragraph("Currency value")));
        table.addCell(new Cell().add(new Paragraph("Share value")));
        table.addCell(new Cell().add(new Paragraph("Total value")));
        table.addCell(new Cell().add(new Paragraph("Stock count")));

        table.addCell(new Cell().add(new Paragraph(customerStats.getCryptocurrencyValue().toString())));
        table.addCell(new Cell().add(new Paragraph(customerStats.getShareValue().toString())));
        table.addCell(new Cell().add(new Paragraph(customerStats.getCurrencyValue().toString())));
        table.addCell(new Cell().add(new Paragraph(customerStats.getTotalValue().toString())));
        table.addCell(new Cell().add(new Paragraph(customerStats.getStockCount().toString())));

        doc.add(table);
        doc.close();

        byte[] bytes = outputStream.toByteArray();
        try {
            response.getOutputStream().write(bytes);
        } catch (IOException e) {
            throw new RuntimeException("Failed to export table to PDF.", e);
        }
    }
}
