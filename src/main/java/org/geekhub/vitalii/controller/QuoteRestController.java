package org.geekhub.vitalii.controller;

import org.geekhub.vitalii.dto.UserStockDTO;
import org.geekhub.vitalii.dto.ChartPointDTO;
import org.geekhub.vitalii.service.QuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@RestController
public class QuoteRestController {

    private final QuoteService quoteService;

    @Autowired
    public QuoteRestController(QuoteService quoteService) {
        this.quoteService = quoteService;
    }

    @ResponseBody
    @PostMapping("/addStock")
    public void addStock(Principal principal, @RequestBody UserStockDTO addStockDTO) {
        quoteService.addStock(principal.getName(), addStockDTO);
    }

    @ResponseBody
    @GetMapping("/getQuoteChart")
    public List<ChartPointDTO> getChartData(@RequestParam(name = "symbol") String symbol) throws IOException {
        return quoteService.getChartPoints(symbol);
    }
}
