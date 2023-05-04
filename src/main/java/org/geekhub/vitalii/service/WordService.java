package org.geekhub.vitalii.service;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.geekhub.vitalii.dto.StockStatsDTO;
import org.geekhub.vitalii.repository.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Service
public class WordService {

    private final WordRepository wordRepository;

    @Autowired
    public WordService(WordRepository wordRepository) {
        this.wordRepository = wordRepository;
    }

    public void getStatsTable(HttpServletResponse response) {
        response.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
        response.setHeader("Content-Disposition",
            "attachment; filename=stocksStats" + LocalDate.now() + ".docx");

        List<StockStatsDTO> stocksStats = wordRepository.getStocksStats();

        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
             XWPFDocument doc = new XWPFDocument()) {

            XWPFTable table = doc.createTable(4, 5);

            table.getRow(0).getCell(0).setText("Asset type");
            table.getRow(0).getCell(1).setText("Asset count");
            table.getRow(0).getCell(2).setText("Avg change in percent");
            table.getRow(0).getCell(3).setText("Total market cap");
            table.getRow(0).getCell(4).setText("Total volume");

            int rowNum = 1;
            for (StockStatsDTO stock : stocksStats) {
                table.getRow(rowNum).getCell(0).setText(stock.getAssetType());
                table.getRow(rowNum).getCell(1).setText(stock.getAssetCount().toString());
                table.getRow(rowNum).getCell(2).setText(stock.getAvgChangeInPercent().toString());
                table.getRow(rowNum).getCell(3).setText(stock.getTotalMarketCap().toString());
                table.getRow(rowNum).getCell(4).setText(stock.getTotalVolume().toString());
                rowNum++;
            }

            doc.write(out);

            response.getOutputStream().write(out.toByteArray());
            response.getOutputStream().flush();
        } catch (IOException e) {
            throw new RuntimeException("Failed to export table to Word.", e);
        }
    }
}
