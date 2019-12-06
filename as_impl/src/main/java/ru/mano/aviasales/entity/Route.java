package ru.mano.aviasales.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Data

public class Route extends AbstractEntityParent {
    private User userId;
    private List<Ticket> tickets = new ArrayList<>();


    public Route(int id, User userId, List<Ticket> tickets) {
        super(id);
        this.userId = userId;
        this.tickets = tickets;
    }

    public boolean addTicket(Ticket ticket) {
        return this.tickets.add(ticket);
    }

    public boolean addTicket(List<Ticket> tickets) {
        return this.tickets.addAll(tickets);
    }

}
