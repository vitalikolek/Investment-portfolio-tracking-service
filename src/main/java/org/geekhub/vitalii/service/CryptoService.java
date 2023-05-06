package org.geekhub.vitalii.service;

import org.geekhub.vitalii.dto.StockDTO;
import org.geekhub.vitalii.repository.CryptoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CryptoService {

    private final CryptoRepository cryptoRepository;
    private final int pageSize = 25;

    @Autowired
    public CryptoService(CryptoRepository cryptoRepository) {
        this.cryptoRepository = cryptoRepository;
    }

    public int getPageCount() {
        return (int) Math.ceil((double) cryptoRepository.cryptocurrenciesCount() / pageSize);
    }

    public List<StockDTO> getCrypto(Integer page) {
        int offset = (page - 1) * pageSize;
        return cryptoRepository.getCryptoInfo(pageSize, offset);
    }

    public List<String> getCryptoSymbols() {
        return cryptoRepository.getAllCryptoSymbols();
    }
}
