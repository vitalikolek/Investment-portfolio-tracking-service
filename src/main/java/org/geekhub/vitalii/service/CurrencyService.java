package org.geekhub.vitalii.service;

import org.geekhub.vitalii.dto.CurrencyDTO;
import org.geekhub.vitalii.repository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CurrencyService {

    private final CurrencyRepository currencyRepository;

    @Autowired
    public CurrencyService(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    public List<CurrencyDTO> getCurrency() {
        return currencyRepository.getCurrencyInfo();
    }
}
