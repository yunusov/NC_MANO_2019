package ru.mano.aviasales.service;

import org.springframework.stereotype.Service;
import ru.mano.aviasales.model.Route;
import ru.mano.aviasales.model.Ticket;
import ru.mano.aviasales.model.User;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class RoutesManagementService {

    private static List<Route> routesStorage = new LinkedList<>();
    private static long nextId;
    private static RoutesManagementService instance;

    static {
        instance = new RoutesManagementService();
    }

    private RoutesManagementService() {
    }

    public static RoutesManagementService getInstance() {
        return instance;
    }

    public long createRoute(List<Ticket> list, User owner) {
        long id = generateNewId();
        routesStorage.add(new Route(id, list, owner));
        return id;
    }

    public Route getRoute(long id) {
        return routesStorage.stream()
                .filter(route -> route.getId() == id)
                .findAny().get();
    }

    public List<Route> getUsersRoutes(User owner) {
        List<Route> result = new LinkedList<>();
        for (Route r : routesStorage) {
            if (r.getOwner().getId() == owner.getId()) {
                result.add(new Route(r));
            }
        }
        return result;
    }

    public void addTicketInRoute(long id, Ticket ticket) {
        Optional<Route> route = Optional.ofNullable(getRoute(id));
        route.ifPresent(route1 -> route1.addNextRoot(ticket));
    }

    public void addTicketAtIndex(long id, int index, Ticket ticket) {
        Optional<Route> route = Optional.ofNullable(getRoute(id));
        route.ifPresent(route1 -> route1.addRootAtIndex(ticket, index));
    }

    public void deleteTicketFromRoute(long id, Ticket ticket) {
        Optional<Route> route = Optional.ofNullable(getRoute(id));
        route.ifPresent(route1 -> route1.deleteRoot(ticket));
    }

    public void deleteRoute(long id) {
        Optional<Route> route = Optional.ofNullable(getRoute(id));
        if (route.isPresent()) {
            routesStorage.remove(routesStorage.indexOf(getRoute(id)));
        } else {
            System.out.println("Can not complete deletion, because user with id " + id + " does not exists");
        }
    }

    private long generateNewId() {
        return nextId++;
    }
}
