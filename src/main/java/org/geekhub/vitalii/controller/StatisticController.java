package org.geekhub.vitalii.controller;

import org.geekhub.vitalii.service.ExcelService;
import org.geekhub.vitalii.service.PDFService;
import org.geekhub.vitalii.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.security.Principal;

@Controller
@RequestMapping("/statistic")
public class StatisticController {

    private final ExcelService excelService;
    private final PDFService pdfService;
    private final WordService wordService;

    @Autowired
    public StatisticController(ExcelService excelService, PDFService pdfService, WordService wordService) {
        this.excelService = excelService;
        this.pdfService = pdfService;
        this.wordService = wordService;
    }

    @GetMapping
    public String statistic() {
        return "statistic";
    }

    @GetMapping("/excel")
    public void exportToExcel(HttpServletResponse response, Principal principal) {
        excelService.exportToExcel(response, principal.getName());
    }

    @GetMapping("/word")
    public void exportToWord(HttpServletResponse response) {
        wordService.getStatsTable(response);
    }

    @GetMapping("/pdf")
    public void exportToPDF(HttpServletResponse response, Principal principal) {
        pdfService.getStatsTable(response, principal.getName());
    }
}
