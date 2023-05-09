package org.geekhub.vitalii.service;

import org.geekhub.vitalii.dto.ChartPointDTO;
import org.geekhub.vitalii.dto.StockDTO;
import org.geekhub.vitalii.dto.UserStockDTO;
import org.geekhub.vitalii.repository.QuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.histquotes.HistoricalQuote;
import yahoofinance.histquotes.Interval;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
public class QuoteService {

    private final QuoteRepository quoteRepository;

    @Autowired
    public QuoteService(QuoteRepository quoteRepository) {
        this.quoteRepository = quoteRepository;
    }

    public StockDTO getStockInfo(String symbol) {
        Stock stock = YahooFinanceHelper.makeStockFromSymbol(symbol);
        return new StockDTO(stock.getSymbol(), stock.getName(), stock.getQuote().getPrice(),
                stock.getQuote().getDayHigh(), stock.getQuote().getDayLow(), stock.getQuote().getChange(),
                stock.getQuote().getChangeInPercent(), stock.getStats().getMarketCap(), stock.getQuote().getVolume());
    }

    public List<ChartPointDTO> getChartPoints(String symbol) throws IOException {
        Calendar from = Calendar.getInstance();
        from.add(Calendar.MONTH, -1);
        Calendar to = Calendar.getInstance();
        List<HistoricalQuote> quotes = YahooFinance.get(symbol).getHistory(from, to, Interval.DAILY);
        List<ChartPointDTO> chartData = new ArrayList<>();
        for (HistoricalQuote quote : quotes) {
            ChartPointDTO chartPoint = new ChartPointDTO();
            chartPoint.setDate(quote.getDate());
            chartPoint.setValue(quote.getClose());
            chartData.add(chartPoint);
        }
        return chartData;
    }

    public void addStock(String username, UserStockDTO stock) {
        quoteRepository.addStock(username, stock);
    }
}
