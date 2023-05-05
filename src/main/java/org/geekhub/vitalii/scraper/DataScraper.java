package org.geekhub.vitalii.scraper;

import org.geekhub.vitalii.dto.StockDTO;
import org.geekhub.vitalii.repository.CryptoRepository;
import org.geekhub.vitalii.repository.CurrencyRepository;
import org.geekhub.vitalii.repository.ShareRepository;
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

    private final CryptoRepository cryptoRepository;
    private final CurrencyRepository currencyRepository;
    private final ShareRepository shareRepository;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DataScraper(CryptoRepository cryptoRepository, CurrencyRepository currencyRepository, ShareRepository shareRepository, JdbcTemplate jdbcTemplate) {
        this.cryptoRepository = cryptoRepository;
        this.currencyRepository = currencyRepository;
        this.shareRepository = shareRepository;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Scheduled(fixedDelay = 300000)
    public void scrapeAndSaveAllData() {
        scrapeAndSave("cryptocurrency", cryptoRepository.getAllCryptoSymbols());
        scrapeAndSave("share", shareRepository.getAllShareSymbols());
        scrapeAndSaveCurrency(currencyRepository.getAllCurrencySymbols());
    }

    public void scrapeAndSave(String stock, List<String> symbols) {
        Map<String, Stock> stockMap = YahooFinanceHelper.makeMapOfStocksFromListOfSymbols(symbols);
        List<StockDTO> scrapedData = YahooFinanceHelper.getDataForScrape(stockMap);
        List<Object[]> dataToUpdate = new ArrayList<>();
        String sql = "UPDATE " + stock + " SET name=?, price=?, dayHigh=?, dayLow=?, change=?, changeInPercent=?, marketCap=?, volume=? WHERE symbol=?;";
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

    public void scrapeAndSaveCurrency(List<String> symbols) {
        Map<String, Stock> stockMap = YahooFinanceHelper.makeMapOfStocksFromListOfSymbols(symbols);
        List<StockDTO> scrapedData = YahooFinanceHelper.getDataForScrape(stockMap);
        List<Object[]> dataToUpdate = new ArrayList<>();
        String sql = "UPDATE currency SET name=?, price=?, dayHigh=?, dayLow=?, change=?, changeInPercent=? WHERE symbol=?;";
        for (StockDTO data : scrapedData) {
            Object[] values = new Object[] {
                data.getName(),
                data.getPrice(),
                data.getDayHigh(),
                data.getDayLow(),
                data.getChange(),
                data.getChangeInPercent(),
                data.getSymbol()
            };
            dataToUpdate.add(values);
        }
        jdbcTemplate.batchUpdate(sql, dataToUpdate);
    }
}
