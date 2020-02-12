package ru.mano.aviasales.test.dto;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import ru.mano.aviasales.dto.Role;
import ru.mano.aviasales.dto.RouteDto;
import ru.mano.aviasales.dto.TicketDto;
import ru.mano.aviasales.dto.UserDto;

import java.util.LinkedList;

@RunWith(JUnit4.class)
public class RouteDtoTests {

    RouteDto route;

    @Before
    public void setUp() throws Exception {
        route = new RouteDto();
    }

    @Test
    public void createTest() throws Exception {
        Assert.assertNotNull(route);
    }

    @Test
    public void gettersTest() throws Exception {
        LinkedList<TicketDto> list = new LinkedList<>();
        UserDto user = new UserDto(0, "name", Role.USER);
        list.add(new TicketDto(0, null, null));
        RouteDto route = new RouteDto(0, list, user);
        Assert.assertEquals(0, route.getId());
        Assert.assertEquals(list, route.getRoute());
        Assert.assertEquals(user, route.getOwner());
    }

    @Test
    public void settersTest() throws Exception {
        long id = 0;
        UserDto user = new UserDto(0, "name", Role.USER);
        LinkedList<TicketDto> list = new LinkedList<>();
        list.add(new TicketDto(0, null, null));
        route.setId(id);
        route.setOwner(user);
        route.setRoute(list);
        Assert.assertEquals(id, route.getId());
        Assert.assertEquals(user, route.getOwner());
        Assert.assertEquals(list, route.getRoute());
    }

    @Test
    public void addNextRootTest() throws Exception {
        route.setRoute(new LinkedList<>());
        route.addNextRoot(new TicketDto(1,null,null));
        Assert.assertEquals(1, route.getRoute().size());
    }

    @Test
    public void addRootAtIndexTest() throws Exception {
        route.setRoute(new LinkedList<>());
        for (int i = 0; i < 4; i++) {
            route.addNextRoot(new TicketDto(i, null, null));
        }
        route.addRootAtIndex(new TicketDto(-1, null, null), 2);
        Assert.assertEquals(-1, route.getRoute().get(2).getId());
    }

    @Test
    public void deleteRootTest() throws Exception {
        route.setRoute(new LinkedList<>());
        TicketDto ticket = new TicketDto(0, null, null);
        route.addNextRoot(ticket);
        route.deleteRoot(ticket);
        Assert.assertEquals(route.getRoute().size(), 0);
    }
}
