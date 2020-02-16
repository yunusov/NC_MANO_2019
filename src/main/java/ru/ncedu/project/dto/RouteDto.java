package ru.ncedu.project.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;


@EqualsAndHashCode(callSuper = true)
@Data
public class RouteDto extends AbstractEntityParent {
    private UserDto userDto;

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }

    private List<TicketDto> tickets = new ArrayList<>();

    public RouteDto(int id, UserDto userDto, TicketDto ticket) {
        super(id);
        this.userDto = userDto;
        this.tickets.add(ticket);
    }
    public RouteDto(int id, UserDto userDto, List<TicketDto> tickets) {
        super(id);
        this.userDto = userDto;
        this.tickets = tickets;
    }

    public RouteDto(int id, UserDto userDto) {
        super(id);
        this.userDto = userDto;
    }

    public List<TicketDto> getTicketList() {
        return tickets;
    }

    public boolean addTicket(TicketDto ticket) {
        return this.tickets.add(ticket);
    }

    public boolean addTicket(List<TicketDto> tickets) {
        return this.tickets.addAll(tickets);
    }

}
