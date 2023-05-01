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

    private final CryptoRepository cryptoRepository;
    private final int pageSize = 25;

    @Autowired
    public CryptoService(CryptoRepository cryptoRepository) {
        this.cryptoRepository = cryptoRepository;
    }

    public int getPage(int page) {
        return page;
    }

    public int getPageCount() {
        return (int) Math.ceil((double) cryptoRepository.cryptocurrenciesCount() / pageSize);
    }

    public List<StockDTO> getCrypto(Integer page) {
        int offset = (getPage(page) - 1) * pageSize;
        List<String> cryptoSymbols = cryptoRepository.getCryptoSymbols(pageSize, offset);
        Map<String, Stock> stockMap = StockHelper.makeMapOfStocksFromListOfSymbols(cryptoSymbols);
        return StockHelper.makeStockDTOSFromStocks(stockMap);
    }
}
