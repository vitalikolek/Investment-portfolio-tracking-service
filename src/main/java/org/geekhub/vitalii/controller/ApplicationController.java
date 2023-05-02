package org.geekhub.vitalii.controller;

import org.geekhub.vitalii.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
public class ApplicationController {

    private final CryptoService cryptoService;
    private final CurrencyService currencyService;
    private final PortfolioService portfolioService;
    private final QuoteService quoteService;
    private final ShareService shareService;

    @Autowired
    public ApplicationController(CryptoService cryptoService, CurrencyService currencyService, PortfolioService portfolioService, QuoteService quoteService, ShareService shareService) {
        this.cryptoService = cryptoService;
        this.currencyService = currencyService;
        this.portfolioService = portfolioService;
        this.quoteService = quoteService;
        this.shareService = shareService;
    }

    @GetMapping("/crypto")
    public String crypto(Model model, @RequestParam(name = "page", defaultValue = "1") Integer page) {
        model.addAttribute("currentPage", page);
        model.addAttribute("pageCount", cryptoService.getPageCount());
        return "crypto";
    }

    @GetMapping("/currency")
    public String currency(Model model) {
        model.addAttribute("currencies", currencyService.getCurrency());
        return "currency";
    }

    @GetMapping("/portfolio")
    public String portfolio(Model model, Principal principal) {
        model.addAttribute("customerStocks", portfolioService.getCustomerStocks(principal.getName()));
        return "portfolio";
    }

    @GetMapping("/quote/{symbol}")
    public String quote(Model model, @PathVariable("symbol") String symbol) {
        model.addAttribute("stockInfo", quoteService.getStockInfo(symbol));
        return "quote";
    }

    @GetMapping("/share")
    public String share(Model model, @RequestParam(name = "page", defaultValue = "1") Integer page) {
        model.addAttribute("currentPage", page);
        model.addAttribute("pageCount", shareService.getPageCount());
        return "share";
    }
}
