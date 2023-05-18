package org.geekhub.vitalii.controller;

import org.geekhub.vitalii.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class CurrencyController {

    private final StockService stockService;

    @Autowired
    public CurrencyController(StockService stockService) {
        this.stockService = stockService;
    }


    @GetMapping("/currency")
    public String currency(Model model, Principal principal) {
        model.addAttribute("currencies", stockService.getStock("currency", 1));
        model.addAttribute("principal", principal);
        return "currency";
    }
}
