package ru.mano.aviasales.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Entity
public class Route {

    @Id
    private long id;

    private List<Ticket> route = new LinkedList<>();
    @Getter
    @Setter
    private User owner;    //заменить int на User

    public Route(long id, List<Ticket> route, User owner) {
        this.id = id;
        this.route = new LinkedList<>(route);
        this.owner = owner;
    }

    public Route(Route route) {
        this.route = new LinkedList<>(route.getRoute());
        this.owner = route.getOwner();
    }

    public Route(long id, List<Ticket> route) {
        this(id, route, null);
    }

    public Route(long id, Ticket[] tickets) {
        this(id, new LinkedList<>(Arrays.asList(tickets)), null);
    }

    public List<Ticket> getRoute() {
        return new LinkedList<>(route);
    }

    public boolean addNextRoot(Ticket newRoot) {
        return route.add(newRoot);
    }

    public void addRootAtIndex(Ticket newRoot, int index) {
        route.add(index, newRoot);
    }

    public void deleteRoot(Ticket ticket) {
        route.removeIf(t -> t.equals(ticket));
    }

    public long getId() {
        return id;
    }
}
