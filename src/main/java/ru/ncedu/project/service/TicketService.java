package ru.ncedu.project.service;

import ru.ncedu.project.dto.CityDto;
import ru.ncedu.project.dto.TicketDto;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class TicketService {

    private List<TicketDto> storage = new ArrayList<>();
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


    public TicketDto getTicket(int id) {
        try {
            return storage.stream()
                    .filter(u -> u.getId() == id)
                    .findAny()
                    .orElseThrow(NoSuchElementException::new);
        } catch (NoSuchElementException e) {
            System.out.println( "Can't get Ticket with id: " + id + '\n' + e.getMessage());
            return null;
        }
    }

    public TicketDto createTicket(int fromId, int toId, double cost) {
        TicketDto ticket = new TicketDto(generateNewId(), cityService.getCity(fromId), cityService.getCity(toId), cost);
        if (storage.add(ticket) )
            return ticket;
        else
            System.out.println( "Can't add in storage new Ticket " + ticket);
        return null;

    }

    public TicketDto createTicket(CityDto from, CityDto to, double cost) {
        TicketDto ticket = new TicketDto(generateNewId(), from, to, cost);
        if (storage.add(ticket) )
            return ticket;
        System.out.println( "Can't add in storage new Ticket " + ticket);
        return null;
    }

    public TicketDto updateTicket(int id, TicketDto ticket) {
        return storage.set(id, ticket);
    }

    @Deprecated
    public TicketDto updateTicketCost(int id, double cost) {
        TicketDto ticket = getTicket(id);
        if (ticket != null) {
            ticket.setCost(cost);
            return ticket;
        }
        System.out.println("Can't change cost. There is no Ticket with id " + id);
        return null;
    }


    @Deprecated
    public TicketDto updateTicketFrom(int id, int fromId) {
        TicketDto ticket = getTicket(id);
        if (ticket != null) {
            ticket.setFrom(cityService.getCity(fromId));
            return ticket;
        }
        System.out.println("Can\'t change City from.There is no Ticket with id " + id);
        return null;
    }

    @Deprecated
    public TicketDto updateTicketTo(int id, int toId) {
        TicketDto ticket = getTicket(id);
        if (ticket != null) {
            ticket.setTo(cityService.getCity(toId));
            return ticket;
        }
        System.out.println("Can\'t change City from.There is no Ticket with id " + id);
        return null;
    }

    public TicketDto deleteTicket(int id) {
        TicketDto ticket = getTicket(id);
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
        TicketDto ticket = getTicket(ticketId);
        if (ticket == null) {
            System.out.println("Can\'t compute distance, because Ticket with id " + ticketId + " does not exists");
            return 0.0;
        }
        return getDistance(ticket);
    }

    private int generateNewId() {
        return nextId++;
    }


    public double getDistance (TicketDto ticket) {
        double resultX = ticket.getFrom().getX() - ticket.getTo().getX();
        double resultY = ticket.getFrom().getY() - ticket.getTo().getY();
        return Math.sqrt(resultX * resultX + resultY * resultY);
    }
}
