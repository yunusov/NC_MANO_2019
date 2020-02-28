package ru.mano.aviasales.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/login")
public class LoginController {

    @GetMapping
    public String showLogin() {
        return "login";
    }

    @GetMapping("/success")
    public String success(Principal principal, Model model) {
        model.addAttribute("name", principal.getName());
        return "success";
    }

}
