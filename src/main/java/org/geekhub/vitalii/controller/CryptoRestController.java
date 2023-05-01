package org.geekhub.vitalii.controller;

import org.geekhub.vitalii.dto.StockDTO;
import org.geekhub.vitalii.service.CryptoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CryptoRestController {

    private final CryptoService cryptoService;

    @Autowired
    public CryptoRestController(CryptoService cryptoService) {
        this.cryptoService = cryptoService;
    }

    @GetMapping("/getCrypto")
    public List<StockDTO> getCrypto(@RequestParam(name = "page", defaultValue = "1") int page) {
        return cryptoService.getCrypto(page);
    }
}
