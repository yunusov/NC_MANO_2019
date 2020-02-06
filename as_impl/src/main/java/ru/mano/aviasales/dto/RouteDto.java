package ru.mano.aviasales.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
public class RouteDto extends BaseDto {

    private UserDto userDtoId;
    private List<TicketDto> tickets = new ArrayList<>();

    public RouteDto(String id, UserDto userDtoId, TicketDto ticketDto) {
        super(id);
        this.userDtoId = userDtoId;
        this.tickets.add(ticketDto);
    }

    public RouteDto(String id, UserDto userDtoId, List<TicketDto> tickets) {
        super(id);
        this.userDtoId = userDtoId;
        this.tickets = tickets;
    }

    public RouteDto(UserDto userDtoId) {
        super();
        this.userDtoId = userDtoId;
    }

    public List<TicketDto> getTicketList() {
        return tickets;
    }


    public boolean addTicket(TicketDto ticketDto) {
        return this.tickets.add(ticketDto);
    }

    public boolean addTicket(List<TicketDto> ticketDtos) {
        return this.tickets.addAll(ticketDtos);
    }

}
