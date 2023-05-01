package org.geekhub.vitalii.service;

import org.geekhub.vitalii.dto.StockDTO;
import org.geekhub.vitalii.repository.ShareRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yahoofinance.Stock;

import java.util.List;
import java.util.Map;

@Service
public class ShareService {

    private final ShareRepository shareRepository;

    @Autowired
    public ShareService(ShareRepository shareRepository) {
        this.shareRepository = shareRepository;
    }

    public List<StockDTO> getShares(Integer limit, Integer offset) {
        List<String> cryptoSymbols = shareRepository.getShareSymbols(limit, offset);
        Map<String, Stock> stockMap = StockHelper.makeMapOfStocksFromListOfSymbols(cryptoSymbols);
        return StockHelper.makeStockDTOSFromStocks(stockMap);
    }
}
