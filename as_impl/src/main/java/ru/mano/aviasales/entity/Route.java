package ru.mano.aviasales.entity;

import java.util.ArrayList;
import java.util.List;

public class Route {
    private User userId;
    private List<Ticket> tickets = new ArrayList<>();

    public Route() {}

    public Route(User userId) {
        this.userId = userId;
    }
    public Route(User userId, Ticket ticket) {
        this.userId = userId;
        this.tickets.add(ticket);
    }

    public Route(User userId, List<Ticket> tickets) {
        this.userId = userId;
        this.tickets.addAll(tickets);
    }

    public boolean addTicket(Ticket ticket) {
        return this.tickets.add(ticket);
    }

    public boolean addTicket(List<Ticket> tickets) {
        return this.tickets.addAll(tickets);
    }

    public List<Ticket> getTickets() {
        return new ArrayList<Ticket>(tickets);
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }
}
