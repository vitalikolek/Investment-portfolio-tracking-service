package org.geekhub.vitalii.service;

import org.geekhub.vitalii.dto.StockInPortfolioDTO;
import org.geekhub.vitalii.repository.PortfolioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PortfolioService {

    private final PortfolioRepository portfolioRepository;

    @Autowired
    public PortfolioService(PortfolioRepository portfolioRepository) {
        this.portfolioRepository = portfolioRepository;
    }

    public List<StockInPortfolioDTO> getCustomerStocks(String username) {
        List<StockInPortfolioDTO> stocksInPortfolio = portfolioRepository.getCustomerStocks(username);

        for (StockInPortfolioDTO stock : stocksInPortfolio) {
            stock.setValue(stock.getAmount().multiply(stock.getPrice()));
        }

        return stocksInPortfolio;
    }

    public void deleteStock(String username, String type, String symbol) {
        portfolioRepository.deleteStock(username, type, symbol);
    }
}
