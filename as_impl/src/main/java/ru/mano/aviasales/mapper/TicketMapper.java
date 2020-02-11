package ru.mano.aviasales.mapper;

import ru.mano.aviasales.dto.TicketDto;
import ru.mano.aviasales.entity.Ticket;

public class TicketMapper {

    public static Ticket mapTo(TicketDto dto) {
        return new Ticket(dto.getId(),
                CityMapper.mapTo(dto.getSource()),
                CityMapper.mapTo(dto.getDestination()));
    }

    public static TicketDto mapTo(Ticket entity) {
        return new TicketDto(entity.getId(),
                CityMapper.mapTo(entity.getSource()),
                CityMapper.mapTo(entity.getDestination()));
    }
}