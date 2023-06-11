package org.geekhub.vitalii.scraper;

import org.geekhub.vitalii.dto.StockDTO;
import org.geekhub.vitalii.repository.StockRepository;
import org.geekhub.vitalii.service.YahooFinanceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import yahoofinance.Stock;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class DataScraper {

    private final StockRepository stockRepository;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DataScraper(StockRepository stockRepository, JdbcTemplate jdbcTemplate) {
        this.stockRepository = stockRepository;
        this.jdbcTemplate = jdbcTemplate;
    }

    //@Scheduled(fixedDelay = 300000)
    public void scrapeAndSaveAllData() {
        scrapeAndSave(stockRepository.getAllStockSymbols());
    }

    public void scrapeAndSave(List<String> symbols) {
        Map<String, Stock> stockMap = YahooFinanceHelper.makeMapOfStocksFromListOfSymbols(symbols);
        List<StockDTO> scrapedData = YahooFinanceHelper.getDataForScrape(stockMap);
        List<Object[]> dataToUpdate = new ArrayList<>();
        String sql = "UPDATE stock SET name=?, price=?, dayHigh=?, dayLow=?, change=?, changeInPercent=?, marketCap=?, volume=? WHERE symbol=?;";
        for (StockDTO data : scrapedData) {
            Object[] values = new Object[] {
                data.getName(),
                data.getPrice(),
                data.getDayHigh(),
                data.getDayLow(),
                data.getChange(),
                data.getChangeInPercent(),
                data.getMarketCap(),
                data.getVolume(),
                data.getSymbol()
            };
            dataToUpdate.add(values);
        }
        jdbcTemplate.batchUpdate(sql, dataToUpdate);
    }
}
