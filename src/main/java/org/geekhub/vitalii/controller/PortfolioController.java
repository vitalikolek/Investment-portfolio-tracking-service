package org.geekhub.vitalii.controller;

import org.geekhub.vitalii.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
public class PortfolioController {

    private final PortfolioService portfolioService;

    @Autowired
    public PortfolioController(PortfolioService portfolioService) {
        this.portfolioService = portfolioService;
    }


    @GetMapping("/portfolio")
    public String portfolio(Model model, Principal principal) {
        model.addAttribute("customerStocks", portfolioService.getCustomerStocks(principal.getName()));
        return "portfolio";
    }

    @PostMapping("/delete/{type}/{symbol}")
    public String delete(Principal principal, @PathVariable("symbol") String symbol, @PathVariable("type") String type) {
        portfolioService.deleteStock(principal.getName(), type, symbol);
        return "redirect:/portfolio";
    }
}
