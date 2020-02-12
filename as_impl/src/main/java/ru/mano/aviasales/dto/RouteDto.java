package ru.mano.aviasales.dto;

import lombok.Data;

import java.util.LinkedList;
import java.util.List;

@Data
public class RouteDto extends BaseEntityDto {

    private List<TicketDto> route = new LinkedList<>();
    private UserDto owner;

    public RouteDto(long id, List<TicketDto> route, UserDto owner) {
        this.id = id;
        this.route = new LinkedList<>(route);
        this.owner = owner;
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

    public void setId(long id) {
        this.id = id;
    }

    public RouteDto() {
    }
}
