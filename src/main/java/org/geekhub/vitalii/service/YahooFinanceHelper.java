package org.geekhub.vitalii.service;

import org.geekhub.vitalii.dto.StockDTO;
import org.geekhub.vitalii.exception.YahooFinanceConnectionFail;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class YahooFinanceHelper {

    private YahooFinanceHelper() {
    }

    public static List<StockDTO> getDataForScrape(Map<String, Stock> stocks) {
        List<StockDTO> stockDTOS = new ArrayList<>();

        for (Stock stock : stocks.values()) {
            stockDTOS.add(new StockDTO(stock.getSymbol(), stock.getName(), stock.getQuote().getPrice(),
                stock.getQuote().getDayHigh(), stock.getQuote().getDayLow(), stock.getQuote().getChange(),
                stock.getQuote().getChangeInPercent(), stock.getStats().getMarketCap(), stock.getQuote().getVolume()));
        }

        return stockDTOS;
    }

    public static Map<String, Stock> makeMapOfStocksFromListOfSymbols(List<String> symbols) {
        editFinalStatic(getClassField("QUOTES_QUERY1V7_BASE_URL"),
            "https://query1.finance.yahoo.com/v6/finance/quote");
        try {
            return YahooFinance.get(symbols.toArray(new String[symbols.size()]));
        } catch (IOException e) {
            throw new YahooFinanceConnectionFail("Cannot connect to Yahoo finance", e);
        }
    }

    public static Stock makeStockFromSymbol(String symbol) {
        editFinalStatic(getClassField("QUOTES_QUERY1V7_BASE_URL"),
            "https://query1.finance.yahoo.com/v6/finance/quote");
        try {
            return YahooFinance.get(symbol);
        } catch (IOException e) {
            throw new YahooFinanceConnectionFail("Cannot connect to Yahoo finance", e);
        }
    }

    private static void editFinalStatic(Field field, Object newValue) {
        field.setAccessible(true);

        try {
            Field modifiersField = Field.class.getDeclaredField("modifiers");
            modifiersField.setAccessible(true);
            modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);
            field.set(null, newValue);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }

    private static Field getClassField(String fieldName) {
        Class<YahooFinance> clazz = YahooFinance.class;
        try {
            return clazz.getDeclaredField(fieldName);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }
}
