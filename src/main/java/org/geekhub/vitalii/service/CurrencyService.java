package org.geekhub.vitalii.service;

import org.geekhub.vitalii.dto.StockDTO;
import org.geekhub.vitalii.repository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yahoofinance.Stock;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class CurrencyService {

    private final CurrencyRepository currencyRepository;

    @Autowired
    public CurrencyService(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    public List<StockDTO> getCurrency() {
        List<String> currencySymbols = currencyRepository.getCurrencySymbols();
        Map<String, Stock> stockMap = StockHelper.makeMapOfStocksFromListOfSymbols(currencySymbols);
        return makeStockDTOSFromStocks(stockMap);
    }

    private List<StockDTO> makeStockDTOSFromStocks(Map<String, Stock> stocks) {
        List<StockDTO> stockDTOS = new ArrayList<>();

        for (Stock stock : stocks.values()) {
            stockDTOS.add(new StockDTO(stock.getSymbol(), stock.getName(), stock.getQuote().getPrice(),
                    stock.getQuote().getChange(), stock.getQuote().getChangeInPercent()));
        }

        return stockDTOS;
    }
}
