package ru.mano.aviasales.model;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Route {
    private List<Ticket> route = new LinkedList<>();
    private int ownerId;

    public Route(List<Ticket> route, int ownerId){
        this.route = new LinkedList<>(route);
        this.ownerId = ownerId;
    }

    public Route(List<Ticket> route) {
        this(route, 0);
    }

    public Route(Ticket[] tickets){
        this(new LinkedList<>(Arrays.asList(tickets)), 0);
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public List<Ticket> getRoute() {
        return new LinkedList<>(route);
    }

    public boolean addNextRoot(Ticket newRoot){
        return route.add(newRoot);
    }

    public void addRootAtIndex(Ticket newRoot, int index){
        route.add(index, newRoot);
    }

    public void deleteRoot(int index){
        route.remove(index);
    }
}
