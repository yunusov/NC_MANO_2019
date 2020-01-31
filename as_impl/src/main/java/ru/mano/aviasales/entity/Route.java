package ru.mano.aviasales.entity;

import lombok.Data;
import lombok.Getter;
import ru.mano.aviasales.dto.AbstractEntityParent;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;


@Getter
@Data
@Entity
public class Route {
    @Id
    private int id;
    private User userId;
    @OneToMany
    private List<Ticket> tickets = new ArrayList<>();

    public Route(int id, User userId, Ticket ticket) {
        this.id = id;
        this.userId = userId;
        this.tickets.add(ticket);
    }
    public Route(int id, User userId, List<Ticket> tickets) {
        this.id = id;
        this.userId = userId;
        this.tickets = tickets;
    }

    public Route(int id, User userId) {
        this.id = id;
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
