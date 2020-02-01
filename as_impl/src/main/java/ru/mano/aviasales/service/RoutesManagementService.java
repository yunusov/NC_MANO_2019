package ru.mano.aviasales.service;

import ru.mano.aviasales.dto.RouteDto;
import ru.mano.aviasales.dto.TicketDto;
import ru.mano.aviasales.dto.UserDto;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class RoutesManagementService {

    private static List<RouteDto> routesStorage = new LinkedList<>();
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

    public long createRoute(List<TicketDto> list, UserDto owner) {
        long id = generateNewId();
        routesStorage.add(new RouteDto(id, list, owner));
        return id;
    }

    public RouteDto getRoute(long id) {
        return routesStorage.stream()
                .filter(route -> route.getId() == id)
                .findAny().get();
    }

    public List<RouteDto> getUsersRoutes(UserDto owner) {
        List<RouteDto> result = new LinkedList<>();
        for (RouteDto r : routesStorage) {
            if (r.getOwner().getId() == owner.getId()) {
                result.add(new RouteDto(r));
            }
        }
        return result;
    }

    public void addTicketInRoute(long id, TicketDto ticket) {
        Optional<RouteDto> route = Optional.ofNullable(getRoute(id));
        route.ifPresent(route1 -> route1.addNextRoot(ticket));
    }

    public void addTicketAtIndex(long id, int index, TicketDto ticket) {
        Optional<RouteDto> route = Optional.ofNullable(getRoute(id));
        route.ifPresent(route1 -> route1.addRootAtIndex(ticket, index));
    }

    public void deleteTicketFromRoute(long id, TicketDto ticket) {
        Optional<RouteDto> route = Optional.ofNullable(getRoute(id));
        route.ifPresent(route1 -> route1.deleteRoot(ticket));
    }

    public void deleteRoute(long id) {
        Optional<RouteDto> route = Optional.ofNullable(getRoute(id));
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
