package ru.mano.aviasales;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.mano.aviasales.dto.CityDto;
import ru.mano.aviasales.dto.TicketDto;
import ru.mano.aviasales.repository.TicketRepository;
import ru.mano.aviasales.service.CityService;
import ru.mano.aviasales.service.TicketService;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TicketTest {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private CityService cityService;

    @Autowired
    private TicketRepository ticketRepository;


    @Test
    public void createTicket(){
        CityDto cityFrom = cityService.createCity("Kazan", 0, 0);
        CityDto cityTo = cityService.createCity("Samara", 100, 100);

        TicketDto ticket = ticketService.createTicket(cityFrom, cityTo, 14000);
        Assert.assertEquals(ticket, ticketService.getTicket(ticket.getId()));
    }


    @Test
    public void updateTicket(){
        CityDto cityFrom = cityService.createCity("Kazan", 0, 0);
        CityDto cityTo = cityService.createCity("Samara", 100, 100);

        TicketDto ticket = ticketService.createTicket(cityFrom, cityTo, 14000);
        TicketDto newTicket = new TicketDto(cityTo, cityFrom, 20000);

        ticketService.updateTicket(ticket.getId(), newTicket);
        Assert.assertEquals(newTicket, ticketService.getTicket((ticket.getId())));
    }

    @Test
    public void deleteTicket() {
        CityDto cityFrom = cityService.createCity("Habarovsk", 0, 0);
        CityDto cityTo = cityService.createCity("Vladivostok", 100, 100);

        TicketDto ticket = ticketService.createTicket(cityFrom, cityTo, 14000);

        ticketService.deleteTicket(ticket.getId());
        if(ticketRepository.existsById(ticket.getId()) == true )
            throw new RuntimeException("Unable to delete user from BD");
    }
}
