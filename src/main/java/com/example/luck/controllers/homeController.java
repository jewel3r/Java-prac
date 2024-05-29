package com.example.luck.controllers;

/*
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class homeController {

    @GetMapping(value = "/")
    public String loadInitialPage() {
        return "home";
    }
}
*/

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class homeController {

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("greeting", "Welcome to our dynamic website!");
        return "home";
    }
}