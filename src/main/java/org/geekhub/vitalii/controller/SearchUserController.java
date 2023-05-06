package org.geekhub.vitalii.controller;

import org.geekhub.vitalii.service.SearchUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SearchUserController {

    private final SearchUserService searchUserService;

    @Autowired
    public SearchUserController(SearchUserService searchUserService) {
        this.searchUserService = searchUserService;
    }

    @GetMapping("/search")
    public String search(Model model, @RequestParam(name = "username", defaultValue = "") String username) {
        model.addAttribute("users", searchUserService.findUser(username));
        return "searchUser";
    }
}
