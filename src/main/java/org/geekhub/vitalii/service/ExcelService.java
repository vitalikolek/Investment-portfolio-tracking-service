package org.geekhub.vitalii.service;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.geekhub.vitalii.dto.ExcelCustomerStockDTO;
import org.geekhub.vitalii.dto.UserStockDTO;
import org.geekhub.vitalii.repository.PortfolioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yahoofinance.Stock;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExcelService {

    private final PortfolioRepository portfolioRepository;

    @Autowired
    public ExcelService(PortfolioRepository portfolioRepository) {
        this.portfolioRepository = portfolioRepository;
    }

    public void exportToExcel(HttpServletResponse response, String username) {
        LocalDate creationTime = LocalDate.now();
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment; filename=stocks" + creationTime + ".xlsx");

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Stocks");

        List<ExcelCustomerStockDTO> customerStocks = getCustomerStocks(username);

        int rowNum = 0;
        Row header = sheet.createRow(rowNum++);
        header.createCell(0).setCellValue("symbol");
        header.createCell(1).setCellValue("name");
        header.createCell(2).setCellValue("type");
        header.createCell(3).setCellValue("amount");
        header.createCell(4).setCellValue("value");

        for (ExcelCustomerStockDTO stock : customerStocks) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(stock.getSymbol());
            row.createCell(1).setCellValue(stock.getName());
            row.createCell(2).setCellValue(stock.getType());
            row.createCell(3).setCellValue(stock.getAmount());
            row.createCell(4).setCellValue(stock.getValue());
        }

        try {
            workbook.write(response.getOutputStream());
            workbook.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<ExcelCustomerStockDTO> getCustomerStocks(String username) {
        List<UserStockDTO> customerStocks = portfolioRepository.getCustomerStocks(username);

        List<ExcelCustomerStockDTO> excelStocks = new ArrayList<>();
        for (UserStockDTO customerStock : customerStocks) {
            Stock stock = StockHelper.makeStockFromSymbol(customerStock.getSymbol());

            double value = customerStock.getAmount().doubleValue() * stock.getQuote().getPrice().doubleValue();
            excelStocks.add(new ExcelCustomerStockDTO(customerStock.getSymbol(), stock.getName(), customerStock.getType(),
                customerStock.getAmount().doubleValue(), value));
        }

        return excelStocks;
    }
}
