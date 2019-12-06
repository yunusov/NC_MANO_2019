package ru.mano.aviasales.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Route extends Id {
    private List<Ticket> route = new LinkedList<>();
    @Getter
    @Setter
    private User owner;    //заменить int на User

    public Route(long id, List<Ticket> route, User owner) {
        this.id = id;
        this.route = new LinkedList<>(route);
        this.owner = owner;
    }

    public Route(Route route){
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

    public void deleteRoot(int index) {
        route.remove(index);
    }

    public long getId() {
        return id;
    }
}
