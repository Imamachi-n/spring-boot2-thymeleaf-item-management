package com.rnaomix.itemmanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {

    @GetMapping({"", "/index"})
    public String getLogin(Model model){

        return "/login/index";
    }

    @GetMapping("/error")
    public String postLogin(Model model){

        model.addAttribute("error", true);
        return "/login/index";
    }
}
