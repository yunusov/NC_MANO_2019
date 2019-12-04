package ru.mano.aviasales;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.mano.aviasales.entity.*;
import ru.mano.aviasales.service.RouteService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class App
{
    public static void main(String[] args)
    {
        SpringApplication.run(App.class, args);
        // kind of Data Base
        List<User> users = Arrays.asList(new User(0, User.Role.ADMIN), new User(1), new User(2));
        List<City> cities = Arrays.asList(new City("Moscow", 0, 0), new City("Samara", 0, 300), new City("Khabarovsk", 0, 1000));
        List<Ticket> tickets = Arrays.asList(new Ticket(cities.get(0), cities.get(1), 4000), new Ticket(cities.get(1), cities.get(2), 25000), new Ticket(cities.get(2), cities.get(0), 27000));



        Route route = new Route(0);
        route.addTicket(tickets);

        System.out.println(RouteService.totalDistance(route));
        System.out.println(RouteService.totalCost(route));

    }
}
