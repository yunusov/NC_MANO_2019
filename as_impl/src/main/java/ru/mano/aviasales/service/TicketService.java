package ru.mano.aviasales.service;

import ru.mano.aviasales.entity.Ticket;

public class TicketService {
    public static double distanceBetween (Ticket ticket) {
        double resultX = ticket.getFrom().getX() - ticket.getTo().getX();
        double resultY = ticket.getFrom().getY() - ticket.getTo().getY();
        return Math.sqrt(resultX * resultX + resultY * resultY);
    }
}
