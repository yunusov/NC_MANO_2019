package ru.mano.aviasales.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
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


    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public TicketDto getTicket(String id) {
        Optional<TicketEntity> ticket = repository.findById(id);
        TicketEntity ticketEntity = ticket.orElseThrow(IllegalArgumentException::new);

        return ticketMapper.from(ticketEntity);
    }


    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public TicketDto createTicket(String fromId, String toId, double cost) {
        TicketDto ticketDto = new TicketDto(cityService.getCity(fromId), cityService.getCity(toId), cost);
        TicketEntity ticketEntity = repository.save(ticketMapper.from(ticketDto));

        return ticketMapper.from(ticketEntity);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public TicketDto createTicket(CityDto from, CityDto to, double cost) {
        TicketDto ticketDto = new TicketDto(from, to, cost);
        TicketEntity ticketEntity = repository.save(ticketMapper.from(ticketDto));

        return ticketMapper.from(ticketEntity);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public TicketDto updateTicket(String ticketId, TicketDto newTicket) {
        if(ticketId != null && repository.existsById(ticketId) ) {
            newTicket.setId(ticketId);
            TicketEntity ticketEntity = repository.save(ticketMapper.from(newTicket));

            return ticketMapper.from(ticketEntity);
        } else {
            throw new IllegalArgumentException("Can\'t update ticket with id " + ticketId);
        }
    }


    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void deleteTicket(String ticketId) {
        if(ticketId != null) {
            repository.deleteById(ticketId);
        } else {
            throw new IllegalArgumentException("Can\'t delete ticket");
        }
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public double getTicketDistanceById(String ticketId) {
        TicketDto ticketDto = getTicket(ticketId);

        return getDistance(ticketDto);
    }


    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public double getDistance (TicketDto ticketDto) {
        double resultX = ticketDto.getFrom().getX() - ticketDto.getTo().getX();
        double resultY = ticketDto.getFrom().getY() - ticketDto.getTo().getY();
        return Math.sqrt(resultX * resultX + resultY * resultY);
    }
}
