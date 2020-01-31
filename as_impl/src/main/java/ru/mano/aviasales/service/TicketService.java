package ru.mano.aviasales.service;

import ru.mano.aviasales.dto.City;
import ru.mano.aviasales.dto.Ticket;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class TicketService {

    private List<Ticket> storage = new ArrayList<>();
    private static TicketService ticketService;
    private CityService cityService = CityService.getInstance();
    private static int nextId = 0;

    static {
        ticketService = new TicketService();
    }

    private TicketService(){
    }

    public static TicketService getInstance(){
        return ticketService;
    }


    public Ticket getTicket(int id) {
        try {
            return storage.stream()
                    .filter(u -> u.getId() == id)
                    .findAny()
                    .orElseThrow(NoSuchElementException::new);
        } catch (NoSuchElementException e) {
            System.out.println( "Can\'t get Ticket with id: " + id + '\n' + e.getMessage());
            return null;
        }
    }

    public Ticket createTicket(int fromId, int toId, double cost) {
        Ticket ticket = new Ticket(generateNewId(), cityService.getCity(fromId), cityService.getCity(toId), cost);
        if (storage.add(ticket) )
            return ticket;
        else
            System.out.println( "Can\'t add in storage new Ticket " + ticket);
        return null;

    }

    public Ticket createTicket(City from, City to, double cost) {
        Ticket ticket = new Ticket(generateNewId(), from, to, cost);
        if (storage.add(ticket) )
            return ticket;
        System.out.println( "Can\'t add in storage new Ticket " + ticket);
        return null;
    }

    public Ticket updateTicket(int id, Ticket ticket) {
        // TODO: checking
        return storage.set(id, ticket);
    }

    @Deprecated
    public Ticket updateTicketCost(int id, double cost) {
        Ticket ticket = getTicket(id);
        if (ticket != null) {
            ticket.setCost(cost);
            return ticket;
        }
        System.out.println("Can\'t change cost. There is no Ticket with id " + id);
        return null;
    }


    @Deprecated
    public Ticket updateTicketFrom(int id, int fromId) {
        Ticket ticket = getTicket(id);
        if (ticket != null) {
            ticket.setFrom(cityService.getCity(fromId));
            return ticket;
        }
        System.out.println("Can\'t change City from.There is no Ticket with id " + id);
        return null;
    }

    @Deprecated
    public Ticket updateTicketTo(int id, int toId) {
        Ticket ticket = getTicket(id);
        if (ticket != null) {
            ticket.setTo(cityService.getCity(toId));
            return ticket;
        }
        System.out.println("Can\'t change City from.There is no Ticket with id " + id);
        return null;
    }

    public Ticket deleteTicket(int id) {
        Ticket ticket = getTicket(id);
        if (ticket == null) {
            System.out.println("Can\'t complete deletion, because Ticket with id " + id + " does not exists");
            return null;
        } else if(!storage.remove(ticket)) {
            System.out.println("Can\'t complete deletion of existing Ticket ");
            return null;
        }
        return ticket;
    }

    public double getTicketDistanceById(int ticketId) {
        Ticket ticket = getTicket(ticketId);
        if (ticket == null) {
            System.out.println("Can\'t compute distance, because Ticket with id " + ticketId + " does not exists");
            return 0.0;
        }
        return getDistance(ticket);
    }

    private int generateNewId() {
        return nextId++;
    }


    public double getDistance (Ticket ticket) {
        double resultX = ticket.getFrom().getX() - ticket.getTo().getX();
        double resultY = ticket.getFrom().getY() - ticket.getTo().getY();
        return Math.sqrt(resultX * resultX + resultY * resultY);
    }
}
