package ru.mano.aviasales;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//TODO: add swagger to security, {+}
//TODO:  clean antMatchers = delete /*, deny access for users to admin panel, {+}
//TODO:  correct request mapping, {+-}
//TODO:  create admin panel,
//TODO:  add logging for spring security. {+-}


@SpringBootApplication
public class App
{
    public static void main(String[] args)
    {
        SpringApplication.run(App.class, args); //Greetings from Gerasimov
    }
}
