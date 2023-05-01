package org.geekhub.vitalii.controller;

import org.geekhub.vitalii.service.ExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;
import java.security.Principal;

@Controller
public class ExcelController {

    private final ExcelService excelService;

    @Autowired
    public ExcelController(ExcelService excelService) {
        this.excelService = excelService;
    }

    @GetMapping("/excel")
    public String excel() {
        return "excel";
    }

    @GetMapping("/download")
    public void exportToExcel(HttpServletResponse response, Principal principal) {
        excelService.exportToExcel(response, principal.getName());
    }
}
