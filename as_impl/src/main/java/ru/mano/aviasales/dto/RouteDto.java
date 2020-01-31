package ru.mano.aviasales.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Getter
@Data
public class RouteDto extends AbstractEntityParent {

    private UserDto userDtoId;
    private List<TicketDto> ticketDtos = new ArrayList<>();

    public RouteDto(int id, UserDto userDtoId, TicketDto ticketDto) {
        super(id);
        this.userDtoId = userDtoId;
        this.ticketDtos.add(ticketDto);
    }
    public RouteDto(int id, UserDto userDtoId, List<TicketDto> ticketDtos) {
        super(id);
        this.userDtoId = userDtoId;
        this.ticketDtos = ticketDtos;
    }

    public RouteDto(int id, UserDto userDtoId) {
        super(id);
        this.userDtoId = userDtoId;
    }

    public List<TicketDto> getTicketList() {
        return ticketDtos;
    }


    public boolean addTicket(TicketDto ticketDto) {
        return this.ticketDtos.add(ticketDto);
    }

    public boolean addTicket(List<TicketDto> ticketDtos) {
        return this.ticketDtos.addAll(ticketDtos);
    }

}
