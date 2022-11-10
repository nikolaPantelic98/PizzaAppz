package com.app.pizzaorderingsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/pos/login")
    public String login() {
        return "login";
    }

    @GetMapping("/")
    public String homePage() {
        return "users";
    }
}
