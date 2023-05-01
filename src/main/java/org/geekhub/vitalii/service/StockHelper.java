package org.geekhub.vitalii.service;

import org.geekhub.vitalii.exception.YahooFinanceConnectionFail;
import org.geekhub.vitalii.dto.StockDTO;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class StockHelper {

    private StockHelper() {
    }

    public static List<StockDTO> makeStockDTOSFromStocks(Map<String, Stock> stocks) {
        List<StockDTO> stockDTOS = new ArrayList<>();

        for (Stock stock : stocks.values()) {
            stockDTOS.add(new StockDTO(stock.getSymbol(), stock.getName(), stock.getQuote().getPrice(),
                    stock.getQuote().getChange(), stock.getQuote().getChangeInPercent(), stock.getStats().getMarketCap(),
                    stock.getQuote().getVolume()));
        }

        return stockDTOS;
    }

    public static Map<String, Stock> makeMapOfStocksFromListOfSymbols(List<String> symbols) {
        try {
            return YahooFinance.get(symbols.toArray(new String[symbols.size()]));
        } catch (IOException e) {
            throw new YahooFinanceConnectionFail("Cannot connect to Yahoo finance", e);
        }
    }

    public static Stock makeStockFromSymbol(String symbol) {
        try {
            return YahooFinance.get(symbol);
        } catch (IOException e) {
            throw new YahooFinanceConnectionFail("Cannot connect to Yahoo finance", e);
        }
    }
}
