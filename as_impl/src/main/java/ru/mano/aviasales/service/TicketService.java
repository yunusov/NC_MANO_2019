package ru.mano.aviasales.service;

import ru.mano.aviasales.entity.Ticket;

import java.util.ArrayList;
import java.util.List;

public class TicketService {

    private List<Ticket> storage;
    private static TicketService ticketService;
    static {
        ticketService = new TicketService();
    }

    private TicketService(){
    }

    public static TicketService getInstance(){
        return ticketService;
    }

    public double distance (Ticket ticket) {
        double resultX = ticket.getFrom().getX() - ticket.getTo().getX();
        double resultY = ticket.getFrom().getY() - ticket.getTo().getY();
        return Math.sqrt(resultX * resultX + resultY * resultY);
    }
}
