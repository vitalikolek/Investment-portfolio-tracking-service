package org.geekhub.vitalii.controller;

import org.geekhub.vitalii.dto.UserStockDTO;
import org.geekhub.vitalii.dto.ChartPointDTO;
import org.geekhub.vitalii.service.QuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Controller
public class QuoteController {

    private final QuoteService quoteService;

    @Autowired
    public QuoteController(QuoteService quoteService) {
        this.quoteService = quoteService;
    }

    @GetMapping("/quote/{symbol}")
    public String quote(Model model, @PathVariable("symbol") String symbol) {
        model.addAttribute("stockInfo", quoteService.getStockInfo(symbol));
        return "quote";
    }

    @PostMapping("/addStock")
    @ResponseBody
    public void addStock(Principal principal, @RequestBody UserStockDTO addStockDTO) {
        quoteService.addStock(principal.getName(), addStockDTO);
    }

    @GetMapping("/getQuoteChart")
    @ResponseBody
    public List<ChartPointDTO> getChartData(@RequestParam(name = "symbol") String symbol) throws IOException {
        return quoteService.getChartPoints(symbol);
    }
}
