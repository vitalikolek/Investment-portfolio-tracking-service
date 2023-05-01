package org.geekhub.vitalii.service;

import org.geekhub.vitalii.dto.StockDTO;
import org.geekhub.vitalii.repository.CryptoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yahoofinance.Stock;

import java.util.List;
import java.util.Map;

@Service
public class CryptoService {

    private final CryptoRepository assetRepository;

    @Autowired
    public CryptoService(CryptoRepository assetRepository) {
        this.assetRepository = assetRepository;
    }

    public List<StockDTO> getCrypto(Integer limit, Integer offset) {
        List<String> cryptoSymbols = assetRepository.getCryptoSymbols(limit, offset);
        Map<String, Stock> stockMap = StockHelper.makeMapOfStocksFromListOfSymbols(cryptoSymbols);
        return StockHelper.makeStockDTOSFromStocks(stockMap);
    }
}
