package ru.mano.aviasales.service;

import ru.mano.aviasales.entity.Route;
import ru.mano.aviasales.entity.Ticket;

public class RouteService {
    public static double totalCost(Route route) {
        return route.getTickets().stream()
                                 .mapToDouble(Ticket::getCost)
                                 .sum();
    }
    public static double totalDistance(Route route) {
        return route.getTickets().stream()
                .mapToDouble(TicketService::distanceBetween)
                .sum();
    }

}
