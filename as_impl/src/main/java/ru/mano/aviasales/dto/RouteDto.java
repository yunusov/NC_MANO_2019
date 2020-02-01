package ru.mano.aviasales.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class RouteDto extends BaseEntityDto {
    private List<TicketDto> route = new LinkedList<>();
    @Getter
    @Setter
    private UserDto owner;    //заменить int на UserDto

    public RouteDto(long id, List<TicketDto> route, UserDto owner) {
        this.id = id;
        this.route = new LinkedList<>(route);
        this.owner = owner;
    }

    public RouteDto(RouteDto route) {
        this.route = new LinkedList<>(route.getRoute());
        this.owner = route.getOwner();
    }

    public RouteDto(long id, List<TicketDto> route) {
        this(id, route, null);
    }

    public RouteDto(long id, TicketDto[] tickets) {
        this(id, new LinkedList<>(Arrays.asList(tickets)), null);
    }

    public List<TicketDto> getRoute() {
        return new LinkedList<>(route);
    }

    public boolean addNextRoot(TicketDto newRoot) {
        return route.add(newRoot);
    }

    public void addRootAtIndex(TicketDto newRoot, int index) {
        route.add(index, newRoot);
    }

    public void deleteRoot(TicketDto ticket) {
        route.removeIf(t -> t.equals(ticket));
    }

    public long getId() {
        return id;
    }
}
