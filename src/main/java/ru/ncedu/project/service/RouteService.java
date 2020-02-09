package ru.ncedu.project.service;

import ru.ncedu.project.dto.RouteDto;
import ru.ncedu.project.dto.TicketDto;
import ru.ncedu.project.dto.UserDto;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class RouteService {
    private List<RouteDto> storage = new ArrayList<>();
    private static RouteService routeService;
    private TicketService ticketService = TicketService.getInstance();
    private UserService userService = UserService.getInstance();
    private static int nextId = 0;

    static {
        routeService = new RouteService();
    }

    private RouteService(){
    }

    public static RouteService getInstance(){
        return routeService;
    }


    public RouteDto getRoute(int id) {
        try {
            return storage.stream()
                    .filter(t -> t.getId() == id)
                    .findAny()
                    .orElseThrow(NoSuchElementException::new);
        } catch (NoSuchElementException e) {
            System.out.println( "Can't get Route with id: " + id + '\n' + e.getMessage());
            return null;
        }
    }




    public RouteDto createRoute(int userId, int ticketId) {
        UserDto user = userService.getUser(userId);
        if(user == null) {
            System.out.println("Cant't create Route. There is no user with id " + userId);
            return null;
        }

        TicketDto ticket = ticketService.getTicket(ticketId);
        if (ticket == null) {
            System.out.println("Cant't create Route. There is no ticket with id " + ticketId);
            return null;
        }

        RouteDto route = new RouteDto(generateNewId(), user, ticket);

        if (storage.add(route) )
            return route;
        else
            System.out.println( "Can\'t add in storage new Route " + route);
        return null;
    }




    public RouteDto createRoute(int userId, int... ticketIds) {
        UserDto user = userService.getUser(userId);
        if(user == null) {
            System.out.println("Cant\'t create Route. There is no user with id " + userId);
            return null;
        }

        RouteDto route = new RouteDto(generateNewId(), user);
        for (int ticket : ticketIds) {
            TicketDto tempTicket = ticketService.getTicket(ticket);
            if (tempTicket == null) {
                System.out.println("Cant\'t create Route. There is no ticket with id " + ticket);
                return null;
            }
            route.addTicket(tempTicket);
        }
        if (storage.add(route) )
            return route;
        else
            System.out.println( "Can't add in storage new Route " + route);
        return null;
    }



    public RouteDto updateRouteUser(int id, int userId) {
        RouteDto route = getRoute(id);
        if (route == null) {
            System.out.println("Cant\'t change Route. There is no Route with id " + id);
            return null;
        }

        UserDto user = userService.getUser(userId);
        if(user == null) {
            System.out.println("Cant\'t change Route. There is no User with id " + userId);
            return null;
        }

        route.setUserDto(user);
        return route;
    }

    public RouteDto updateRouteTickets(int id, int index, int newTicketId) {
        RouteDto route = getRoute(id);
        if (route == null) {
            System.out.println("Cant\'t change Route. There is no Route with id " + id);
            return null;
        }
        if (route.getTicketList().size() < index) {
            System.out.println("Cant\'t change Route. There is no ticket with index " + index);
            return null;
        }
        TicketDto ticket = ticketService.getTicket(newTicketId);
        if (ticket == null) {
            System.out.println("Cant\'t change Route. There is no ticket with id " + newTicketId);
            return null;
        }
        route.getTicketList().set(index, ticket);

        return route;
    }

    public RouteDto deleteRoute(int id) {
        RouteDto route = getRoute(id);
        if (route == null) {
            System.out.println("Can\'t complete deletion, because Route with id " + id + " does not exists");
            return null;
        } else if(!storage.remove(route)) {
            System.out.println("Can\'t complete deletion of existing Route ");
            return null;
        }
        return route;
    }

    public double getRouteDistanceById(int routeId) {
        RouteDto route = getRoute(routeId);
        if (route == null) {
            System.out.println("Can\'t compute distance, because Route with id " + routeId + " does not exists");
            return 0.0;
        }
        return totalDistance(route);
    }

    public double getRouteCostById(int routeId) {
        RouteDto route = getRoute(routeId);
        if (route == null) {
            System.out.println("Can\'t compute total cost, because Route with id " + routeId + " does not exists");
            return 0.0;
        }
        return totalCost(route);
    }
    private int generateNewId() {
        return nextId++;
    }

    private double totalCost(RouteDto route) {
        return route.getTickets().stream()
                                 .mapToDouble(TicketDto::getCost)
                                 .sum();
    }
    private double totalDistance(RouteDto route) {
        return route.getTickets().stream()
                .mapToDouble(ticketService::getDistance)
                .sum();
    }

}
