package ru.mano.aviasales.service;

import ru.mano.aviasales.dto.Route;
import ru.mano.aviasales.dto.Ticket;
import ru.mano.aviasales.dto.User;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class RouteService {
    private List<Route> storage = new ArrayList<>();
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


    public Route getRoute(int id) {
        try {
            return storage.stream()
                    .filter(t -> t.getId() == id)
                    .findAny()
                    .orElseThrow(NoSuchElementException::new);
        } catch (NoSuchElementException e) {
            System.out.println( "Can\'t get Route with id: " + id + '\n' + e.getMessage());
            return null;
        }
    }




    public Route createRoute(int userId, int ticketId) {
        User user = userService.getUser(userId);
        if(user == null) {
            System.out.println("Cant\'t create Route. There is no user with id " + userId);
            return null;
        }

        Ticket ticket = ticketService.getTicket(ticketId);
        if (ticket == null) {
            System.out.println("Cant\'t create Route. There is no ticket with id " + ticketId);
            return null;
        }

        Route route = new Route(generateNewId(), user, ticket);

        if (storage.add(route) )
            return route;
        else
            System.out.println( "Can\'t add in storage new Route " + route);
        return null;
    }




    public Route createRoute(int userId, int... ticketIds) {
        User user = userService.getUser(userId);
        if(user == null) {
            System.out.println("Cant\'t create Route. There is no user with id " + userId);
            return null;
        }

        Route route = new Route(generateNewId(), user);
        for (int ticket : ticketIds) {
            Ticket tempTicket = ticketService.getTicket(ticket);
            if (tempTicket == null) {
                System.out.println("Cant\'t create Route. There is no ticket with id " + ticket);
                return null;
            }
            route.addTicket(tempTicket);
        }
        if (storage.add(route) )
            return route;
        else
            System.out.println( "Can\'t add in storage new Route " + route);
        return null;
    }



    public Route updateRouteUser(int id, int userId) {
        Route route = getRoute(id);
        if (route == null) {
            System.out.println("Cant\'t change Route. There is no Route with id " + id);
            return null;
        }

        User user = userService.getUser(userId);
        if(user == null) {
            System.out.println("Cant\'t change Route. There is no User with id " + userId);
            return null;
        }

        route.setUserId(user);
        return route;
    }

    public Route updateRouteTickets(int id, int index, int newTicketId) {
        Route route = getRoute(id);
        if (route == null) {
            System.out.println("Cant\'t change Route. There is no Route with id " + id);
            return null;
        }
        if (route.getTicketList().size() < index) {
            System.out.println("Cant\'t change Route. There is no ticket with index " + index);
            return null;
        }
        Ticket ticket = ticketService.getTicket(newTicketId);
        if (ticket == null) {
            System.out.println("Cant\'t change Route. There is no ticket with id " + newTicketId);
            return null;
        }
        route.getTicketList().set(index, ticket);

        return route;
    }

    public Route deleteRoute(int id) {
        Route route = getRoute(id);
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
        Route route = getRoute(routeId);
        if (route == null) {
            System.out.println("Can\'t compute distance, because Route with id " + routeId + " does not exists");
            return 0.0;
        }
        return totalDistance(route);
    }

    public double getRouteCostById(int routeId) {
        Route route = getRoute(routeId);
        if (route == null) {
            System.out.println("Can\'t compute total cost, because Route with id " + routeId + " does not exists");
            return 0.0;
        }
        return totalCost(route);
    }
    private int generateNewId() {
        return nextId++;
    }

    private double totalCost(Route route) {
        return route.getTickets().stream()
                                 .mapToDouble(Ticket::getCost)
                                 .sum();
    }
    private double totalDistance(Route route) {
        return route.getTickets().stream()
                .mapToDouble(ticketService::getDistance)
                .sum();
    }

}
