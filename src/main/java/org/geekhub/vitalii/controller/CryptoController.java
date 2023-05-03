package org.geekhub.vitalii.controller;

import org.geekhub.vitalii.dto.StockDTO;
import org.geekhub.vitalii.service.CryptoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class CryptoController {

    private final CryptoService cryptoService;

    @Autowired
    public CryptoController(CryptoService cryptoService) {
        this.cryptoService = cryptoService;
    }

    @GetMapping("/crypto")
    public String crypto(Model model, @RequestParam(name = "page", defaultValue = "1") Integer page) {
        model.addAttribute("currentPage", page);
        model.addAttribute("pageCount", cryptoService.getPageCount());
        return "crypto";
    }

    @GetMapping("/getCrypto")
    @ResponseBody
    public List<StockDTO> getCrypto(@RequestParam(name = "page", defaultValue = "1") int page) {
        return cryptoService.getCrypto(page);
    }
}
