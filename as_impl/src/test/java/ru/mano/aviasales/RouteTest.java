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
        CityDto a = cityService.createCity("A", -6, 4);
        CityDto b = cityService.createCity("B", 4, -2);
        CityDto c = cityService.createCity("C", -4, 2);
        CityDto d = cityService.createCity("D", 0, 0);
        CityDto e = cityService.createCity("E", 2, 3);
        CityDto f = cityService.createCity("F", -3, -3);
        CityDto g = cityService.createCity("G", 6, 1);
        CityDto h = cityService.createCity("H", 2, -5);
        CityDto i = cityService.createCity("I", -1, 3);
        CityDto j = cityService.createCity("J", -7, 9);
        CityDto k = cityService.createCity("k", -9, 4);

        ticketService.createTicket(a, c, 1400);
        ticketService.createTicket(c, d, 1400);
        ticketService.createTicket(d, b, 1400);
        ticketService.createTicket(a, i, 1400);
        ticketService.createTicket(a, f, 1400);
        ticketService.createTicket(c, i, 1000);
        ticketService.createTicket(i, e, 1400);
        ticketService.createTicket(f, h, 1400);
        ticketService.createTicket(f, d, 1400);
        ticketService.createTicket(e, d, 1400);
        ticketService.createTicket(e, g, 1400);
        ticketService.createTicket(g, b, 1400);
        ticketService.createTicket(h, b, 1400);
        ticketService.createTicket(a, j, 1400);
        ticketService.createTicket(a, k, 1400);
        ticketService.createTicket(k, b, 1400);

        UserDto user = userService.createUser("Person");

        List<RouteDto> routeDtos = routeService.searchRoutes(a.getId(), b.getId(), user.getId());
        routeDtos.forEach(System.out::println);

    }
}



