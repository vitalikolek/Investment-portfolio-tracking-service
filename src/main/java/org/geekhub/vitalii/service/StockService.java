package org.geekhub.vitalii.service;

import org.geekhub.vitalii.dto.StockDTO;
import org.geekhub.vitalii.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockService {

    private final StockRepository stockRepository;
    private final int pageSize = 25;

    @Autowired
    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    public int getPageCount(String type) {
        return (int) Math.ceil((double) stockRepository.getStockCount(type) / pageSize);
    }

    public List<StockDTO> getStock(String stockType, Integer page) {
        int offset = (page - 1) * pageSize;
        return stockRepository.getStockInfo(stockType, pageSize, offset);
    }

    public List<String> getCryptoSymbols() {
        return stockRepository.getCryptoSymbols();
    }

    public List<String> getShareSymbols() {
        return stockRepository.getShareSymbols();
    }
}
