package ru.mano.aviasales.entity;

import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Getter
@Data
public class Route extends AbstractEntityParent {
    private User userId;
    private List<Ticket> tickets = new ArrayList<>();

    public Route(int id, User userId, Ticket ticket) {
        super(id);
        this.userId = userId;
        this.tickets.add(ticket);
    }
    public Route(int id, User userId, List<Ticket> tickets) {
        super(id);
        this.userId = userId;
        this.tickets = tickets;
    }

    public Route(int id, User userId) {
        super(id);
        this.userId = userId;
    }

    public List<Ticket> getTicketList() {
        return tickets;
    }


    public boolean addTicket(Ticket ticket) {
        return this.tickets.add(ticket);
    }

    public boolean addTicket(List<Ticket> tickets) {
        return this.tickets.addAll(tickets);
    }

}
