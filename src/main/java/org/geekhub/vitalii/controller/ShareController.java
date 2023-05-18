package org.geekhub.vitalii.controller;

import org.geekhub.vitalii.dto.StockDTO;
import org.geekhub.vitalii.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/share")
public class ShareController {

   private final StockService stockService;
   private final String type = "share";

   @Autowired
    public ShareController(StockService stockService) {
        this.stockService = stockService;
    }

    @GetMapping
    public String share(Model model, @RequestParam(name = "page", defaultValue = "1") Integer page, Principal principal) {
        model.addAttribute("currentPage", page);
        model.addAttribute("pageCount", stockService.getPageCount(type));
        model.addAttribute("principal", principal);
        return "share";
    }

    @GetMapping("/getShare")
    @ResponseBody
    public List<StockDTO> getCrypto(@RequestParam(name = "page", defaultValue = "1") int page) {
        return stockService.getStock(type, page);
    }

    @GetMapping("/getShareSymbols")
    @ResponseBody
    public List<String> getCrypto() {
        return stockService.getShareSymbols();
    }
}
