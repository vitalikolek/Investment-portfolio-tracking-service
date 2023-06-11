package org.geekhub.vitalii.service;

import org.geekhub.vitalii.dto.StockInPortfolioDTO;
import org.geekhub.vitalii.entity.Customer;
import org.geekhub.vitalii.repository.CustomerRepository;
import org.geekhub.vitalii.repository.PortfolioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
public class PortfolioService {

    private final PortfolioRepository portfolioRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    public PortfolioService(PortfolioRepository portfolioRepository, CustomerRepository customerRepository) {
        this.portfolioRepository = portfolioRepository;
        this.customerRepository = customerRepository;
    }

    public List<StockInPortfolioDTO> getCustomerStocks(String username) {
        List<StockInPortfolioDTO> stocksInPortfolio = portfolioRepository.getCustomerStocks(username);

        for (StockInPortfolioDTO stock : stocksInPortfolio) {
            stock.setValue(stock.getAmount().multiply(stock.getPrice()));
        }

        return stocksInPortfolio;
    }

    public BigDecimal getSumOfStocksInBitcoin(String username) {
        List<StockInPortfolioDTO> stocks = getCustomerStocks(username);

        BigDecimal totalValue = BigDecimal.valueOf(0);
        for (StockInPortfolioDTO stock : stocks) {
            totalValue = totalValue.add(stock.getValue());
        }

        return totalValue.divide(portfolioRepository.getBitcoinPrice(), RoundingMode.HALF_DOWN);
    }

    public String getCustomerRole(String username) {
        Example<Customer> usernameExample = Example.of(new Customer(null, username, null, null, null, null));
        return customerRepository.findBy(usernameExample, FluentQuery.FetchableFluentQuery::first).get().getRole().getRole();
    }

    public void deleteStock(String username, String symbol) {
        portfolioRepository.deleteStock(username, symbol);
    }
}
