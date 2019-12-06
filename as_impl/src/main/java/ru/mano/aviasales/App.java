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
        List<User> users = Arrays.asList(new User(0, User.Role.ADMIN), new User(1, User.Role.USER));
        List<City> cities = Arrays.asList(new City(0,"Moscow", 0, 0), new City(1,"Samara", 0, 300), new City(2,"Khabarovsk", 0, 1000));
        List<Ticket> tickets = Arrays.asList(new Ticket(1, cities.get(0), cities.get(1), 4000), new Ticket(2, cities.get(1), cities.get(2), 25000), new Ticket(3, cities.get(2), cities.get(0), 27000));



        Route route = new Route(0,users.get(0), tickets);
        RouteService routeService = new RouteService();
        System.out.println(routeService.totalDistance(route));
        System.out.println(routeService.totalCost(route));

    }
}
