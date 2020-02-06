package ru.mano.aviasales.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.mano.aviasales.dto.TicketDto;
import ru.mano.aviasales.entity.TicketEntity;

import java.util.List;

@Component
public class TicketMapper {
    @Autowired
    private CityMapper cityMapper;


    public TicketDto from (TicketEntity ticketEntity) {
        return new TicketDto(
                ticketEntity.getId(),
                cityMapper.from(ticketEntity.getFrom()),
                cityMapper.from(ticketEntity.getTo()),
                ticketEntity.getCost()
        );
    }

    public TicketEntity from (TicketDto ticketDto) {
        return new TicketEntity(
                ticketDto.getId(),
                cityMapper.from(ticketDto.getFrom()),
                cityMapper.from(ticketDto.getTo()),
                ticketDto.getCost()
        );
    }

    //public List<TicketDto> to
}
