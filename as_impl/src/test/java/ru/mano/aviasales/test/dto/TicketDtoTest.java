package ru.mano.aviasales.test.dto;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import ru.mano.aviasales.dto.CityDto;
import ru.mano.aviasales.dto.TicketDto;

@RunWith(JUnit4.class)
public class TicketDtoTest {

    @Test
    public void createTest() throws Exception {
        TicketDto ticket = new TicketDto(0,
                new CityDto(0, 0, 0, null),
                new CityDto(0, 0, 0, null));
        Assert.assertNotNull(ticket);
    }

    @Test
    public void equalsTest() throws Exception {
        CityDto source = new CityDto(0, 1, 2, "a");
        CityDto destination = new CityDto(3, 4, 5, "b");
        TicketDto ticket1 = new TicketDto(0, source, destination);
        TicketDto ticket2 = new TicketDto(0, source, destination);
        Assert.assertTrue(ticket1.equals(ticket2));
    }

    @Test
    public void gettersTest() throws Exception {
        CityDto source = new CityDto(0, 1, 2, "a");
        CityDto destination = new CityDto(3, 4, 5, "b");
        TicketDto ticket = new TicketDto(0, source, destination);
        Assert.assertEquals(ticket.getSource(), source);
        Assert.assertEquals(ticket.getDestination(), destination);
        Assert.assertEquals(0, ticket.getId());
    }

    @Test
    public void settersTest() throws Exception {
        CityDto source = new CityDto(0, 1, 2, "a");
        CityDto destination = new CityDto(3, 4, 5, "b");
        TicketDto ticket = new TicketDto(0, null, null);
        ticket.setSource(source);
        ticket.setDestination(destination);
        ticket.setId(1);
        Assert.assertEquals(source, ticket.getSource());
        Assert.assertEquals(destination, ticket.getDestination());
        Assert.assertEquals(1, ticket.getId());
    }
}
