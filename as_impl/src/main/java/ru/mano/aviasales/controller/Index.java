package ru.mano.aviasales.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class Index {
  
    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    /*

    Created a branch, made a commit and pushed my repository

    */

}
