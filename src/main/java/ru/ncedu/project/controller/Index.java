package ru.ncedu.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class Index {
    @RequestMapping ("/")
    public String startPage() {
        return "index";
    }
}
