package org.geekhub.vitalii.controller;

import org.geekhub.vitalii.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CurrencyController {

    private final CurrencyService currencyService;

    @Autowired
    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping("/currency")
    public String currency(Model model) {
        model.addAttribute("currencies", currencyService.getCurrency());
        return "currency";
    }
}
