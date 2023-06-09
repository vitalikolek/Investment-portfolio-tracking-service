package org.geekhub.vitalii.controller;

import org.geekhub.vitalii.service.PortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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
        String username = principal.getName();
        model.addAttribute("customerStocks", portfolioService.getCustomerStocks(username));
        model.addAttribute("role", portfolioService.getCustomerRole(username));
        model.addAttribute("sumOfStocksInBitcoin", portfolioService.getSumOfStocksInBitcoin(username));
        return "portfolio";
    }

    @GetMapping("/portfolio/{username}")
    public String portfolio(Model model, @PathVariable("username") String username) {
        if (portfolioService.getCustomerRole(username).equals("ROLE_COMPANY")) {
            return "redirect:/search";
        }
        model.addAttribute("customerStocks", portfolioService.getCustomerStocks(username));
        model.addAttribute("username", username);
        return "portfolio";
    }

    @PostMapping("/delete/{symbol}")
    public String delete(Principal principal, @PathVariable("symbol") String symbol) {
        portfolioService.deleteStock(principal.getName(), symbol);
        return "redirect:/portfolio";
    }
}
