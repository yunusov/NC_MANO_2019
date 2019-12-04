package ru.mano.aviasales.entity;

import java.util.ArrayList;
import java.util.List;

public class Route {
    private int userId;
    private List<Ticket> tickets = new ArrayList<>();

    public Route() {}

    public Route(int userId) {
        this.userId = userId;
    }
    public Route(int userId, Ticket ticket) {
        this.userId = userId;
        this.tickets.add(ticket);
    }

    public Route(int userId, List<Ticket> tickets) {
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
