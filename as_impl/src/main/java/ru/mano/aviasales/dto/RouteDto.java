package ru.mano.aviasales.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import ru.mano.aviasales.entity.Route;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Data
@AllArgsConstructor
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

    public RouteDto() {
    }

    public UserDto getOwner() {
        return owner;
    }
}
