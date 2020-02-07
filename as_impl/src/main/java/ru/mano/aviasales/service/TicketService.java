package ru.mano.aviasales.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.mano.aviasales.dto.CityDto;
import ru.mano.aviasales.dto.TicketDto;
import ru.mano.aviasales.entity.TicketEntity;
import ru.mano.aviasales.mapper.TicketMapper;
import ru.mano.aviasales.repository.TicketRepository;
import java.util.Optional;


@Component
public class TicketService {

    @Autowired
    private TicketRepository repository;

    @Autowired
    private TicketMapper ticketMapper;

    @Autowired
    private CityService cityService;

    public TicketDto getTicket(String id) {
        Optional<TicketEntity> ticket = repository.findById(id);

        TicketEntity ticketEntity = ticket.orElseThrow(IllegalArgumentException::new);
        return ticketMapper.from(ticketEntity);
    }

    public TicketDto createTicket(String fromId, String toId, double cost) {
        TicketDto ticketDto = new TicketDto(cityService.getCity(fromId), cityService.getCity(toId), cost);
        repository.save(ticketMapper.from(ticketDto));   //стоит ли проверять РК на уникальность?

        return ticketDto;
    }

    public TicketDto createTicket(CityDto from, CityDto to, double cost) {
        TicketDto ticketDto = new TicketDto(from, to, cost);
        repository.save(ticketMapper.from(ticketDto));

        return ticketDto;
    }

    public TicketDto updateTicket(String ticketId, TicketDto newTicket) {
        if(ticketId != null && repository.existsById(ticketId) ) {
            newTicket.setId(ticketId);
            repository.deleteById(ticketId);
            repository.save(ticketMapper.from(newTicket));

            return newTicket;
        } else
            throw new IllegalArgumentException("Can\'t update ticket with id " + ticketId);
    }


    public TicketDto deleteTicket(String ticketId) {
        if(ticketId != null) {
            TicketDto deleted = getTicket(ticketId);
            repository.deleteById(ticketId);
            return deleted;
        } else
            return null;
    }

    public double getTicketDistanceById(String ticketId) {
        TicketDto ticketDto = getTicket(ticketId);

        return getDistance(ticketDto);
    }

    public double getDistance (TicketDto ticketDto) {
        double resultX = ticketDto.getFrom().getX() - ticketDto.getTo().getX();
        double resultY = ticketDto.getFrom().getY() - ticketDto.getTo().getY();
        return Math.sqrt(resultX * resultX + resultY * resultY);
    }
}
