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


}
