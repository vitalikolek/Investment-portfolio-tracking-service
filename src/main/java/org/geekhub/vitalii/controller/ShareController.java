package org.geekhub.vitalii.controller;

import org.geekhub.vitalii.dto.StockDTO;
import org.geekhub.vitalii.service.ShareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/share")
public class ShareController {

    private final ShareService shareService;

    @Autowired
    public ShareController(ShareService shareService) {
        this.shareService = shareService;
    }

    @GetMapping
    public String share(Model model, @RequestParam(name = "page", defaultValue = "1") Integer page) {
        model.addAttribute("currentPage", page);
        model.addAttribute("pageCount", shareService.getPageCount());
        return "share";
    }

    @GetMapping("/getShare")
    @ResponseBody
    public List<StockDTO> getCrypto(@RequestParam(name = "page", defaultValue = "1") int page) {
        return shareService.getShares(page);
    }
}
