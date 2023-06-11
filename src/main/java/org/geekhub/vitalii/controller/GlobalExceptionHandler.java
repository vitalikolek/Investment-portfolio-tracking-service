package org.geekhub.vitalii.controller;

import org.geekhub.vitalii.exception.UserAlreadyExistsException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserAlreadyExistsException.class)
    public String handleException(Exception e, Model model) {
        String errorMessage = e.getMessage();
        model.addAttribute("errorMessage", errorMessage);
        return "exception/error";
    }
}
