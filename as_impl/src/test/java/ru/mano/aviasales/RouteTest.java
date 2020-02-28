package ru.mano.aviasales;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.mano.aviasales.dto.CityDto;
import ru.mano.aviasales.dto.RouteDto;
import ru.mano.aviasales.dto.TicketDto;
import ru.mano.aviasales.dto.UserDto;
import ru.mano.aviasales.repository.RouteRepository;
import ru.mano.aviasales.service.CityService;
import ru.mano.aviasales.service.RouteService;
import ru.mano.aviasales.service.TicketService;
import ru.mano.aviasales.service.UserService;

import java.util.List;
import java.util.Random;

@SpringBootTest
@RunWith(SpringRunner.class)
public class RouteTest {

    @Autowired
    private UserService userService;

    @Autowired
    private TicketService ticketService;

    @Autowired
    private CityService cityService;

    @Autowired
    private RouteService routeService;

    @Autowired
    private RouteRepository routeRepository;



    @Test
    public void createRoute(){
        CityDto cityFrom = cityService.createCity("Kazan", 0, 0);
        CityDto cityTo = cityService.createCity("Samara", 100, 100);

        CityDto cityFrom2 = cityService.createCity("Khabarovsk", 0, 0);
        CityDto cityTo2 = cityService.createCity("Vladivostok", 100, 100);


        TicketDto ticket = ticketService.createTicket(cityFrom, cityTo, 14000);
        TicketDto ticket2 = ticketService.createTicket(cityFrom2, cityTo2, 18000);

        UserDto user = userService.createUser("Person");

        RouteDto routeDto = routeService.createRoute(user.getId(), ticket.getId(), ticket2.getId());

        Assert.assertEquals(routeDto, routeService.getRoute(routeDto.getId()));
    }

    @Test
    public void searchRoutes() {
        int cityAmount = 11;
        CityDto[] cityDtos = new CityDto[cityAmount];
        int[][] positions = {
                {-6, 4},   // {x, y}
                { 4,-2},
                {-4, 2},
                { 0, 0},
                { 2, 3},
                {-3,-3},
                { 6, 1},
                {2, -5},
                {-1, 3},
                {-7, 9},
                {-9, 4}
        };
        char cityName = 'A';
        for (int i=0; i<positions.length; ++i) {
            cityDtos[i] = cityService.createCity(String.valueOf(cityName++), positions[i][0], positions[i][1]);
        }

        int[][] tickets = {
                {0,  2},   //from A(0) to C(2)
                {0,  5},
                {0,  8},
                {0,  9},
                {0, 10},
                {2,  3},
                {2,  8},
                {3,  1},
                {4,  3},
                {4,  6},
                {5,  3},
                {5,  7},
                {6,  1},
                {7,  1},
                {8,  4},
                {10, 1},
        };

        Random random = new Random();
        for (int i=0; i<tickets.length; ++i) {
            ticketService.createTicket(cityDtos[tickets[i][0]], cityDtos[tickets[i][1]], random.nextInt(1001));
        }

        UserDto user = userService.createUser("Person");

        List<RouteDto> routeDtos = routeService.searchRoutes("A", "B", user.getId());
        routeDtos.forEach(System.out::println);

    }
}

