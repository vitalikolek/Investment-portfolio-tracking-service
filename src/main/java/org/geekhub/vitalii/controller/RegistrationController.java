package org.geekhub.vitalii.controller;

import org.geekhub.vitalii.entity.Customer;
import org.geekhub.vitalii.exception.UserAlreadyExistsException;
import org.geekhub.vitalii.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {

    private final RegistrationService registrationService;

    @Autowired
    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @GetMapping("/registration")
    public String showRegistrationForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "registration";
    }

    @PostMapping("/registration")
    public String register(@ModelAttribute("customer") Customer customer) {
        if (registrationService.isCustomerExist(customer.getUsername(), customer.getEmail())) {
            throw new UserAlreadyExistsException("User with such username or email already exists");
        }
        registrationService.register(customer);
        return "redirect:/login";
    }
}
