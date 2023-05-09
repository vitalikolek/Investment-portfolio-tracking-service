package org.geekhub.vitalii.service;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.geekhub.vitalii.dto.ExcelCustomerStockDTO;
import org.geekhub.vitalii.repository.ExcelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Service
public class ExcelService {

    private final ExcelRepository excelRepository;

    @Autowired
    public ExcelService(ExcelRepository excelRepository) {
        this.excelRepository = excelRepository;
    }

    public void exportToExcel(HttpServletResponse response, String username) {
        List<ExcelCustomerStockDTO> customerStocks = getCustomerStocks(username);

        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment; filename=portfolio" + LocalDate.now() + ".xlsx");

        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Stocks");

            int rowNum = 0;
            Row header = sheet.createRow(rowNum++);
            header.createCell(0).setCellValue("symbol");
            header.createCell(1).setCellValue("name");
            header.createCell(2).setCellValue("amount");
            header.createCell(3).setCellValue("value");

            for (ExcelCustomerStockDTO stock : customerStocks) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(stock.getSymbol());
                row.createCell(1).setCellValue(stock.getName());
                row.createCell(2).setCellValue(stock.getAmount());
                row.createCell(3).setCellValue(stock.getValue());
            }

            workbook.write(response.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException("Failed to export data to Excel.", e);
        }
    }

    public List<ExcelCustomerStockDTO> getCustomerStocks(String username) {
        List<ExcelCustomerStockDTO> excelCustomerStocks = excelRepository.getCustomerStocks(username);

        for (ExcelCustomerStockDTO stock : excelCustomerStocks) {
            stock.setValue(stock.getAmount() * stock.getPrice());
        }

        return excelCustomerStocks;
    }
}
