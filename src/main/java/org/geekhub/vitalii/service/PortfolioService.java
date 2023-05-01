package org.geekhub.vitalii.service;

import org.geekhub.vitalii.dto.StockDTO;
import org.geekhub.vitalii.dto.UserStocksDTO;
import org.geekhub.vitalii.repository.PortfolioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yahoofinance.Stock;

import java.util.ArrayList;
import java.util.List;

@Service
public class PortfolioService {

    private final PortfolioRepository portfolioRepository;

    @Autowired
    public PortfolioService(PortfolioRepository portfolioRepository) {
        this.portfolioRepository = portfolioRepository;
    }

    public List<StockDTO> getCustomerStocks(String username) {
        List<UserStocksDTO> symbolAndPrice = portfolioRepository.getCustomerStocks(username);

        List<StockDTO> stockDTOS = new ArrayList<>();
        for (UserStocksDTO userStocksDTO : symbolAndPrice) {
            Stock stock = StockHelper.makeStockFromSymbol(userStocksDTO.getSymbol());
            stockDTOS.add(new StockDTO(stock.getSymbol(), stock.getName(), userStocksDTO.getType(),
                stock.getQuote().getPrice(), stock.getQuote().getChangeInPercent(), userStocksDTO.getAmount(),
                userStocksDTO.getAmount().multiply(stock.getQuote().getPrice())));
        }

        return stockDTOS;
    }
}
