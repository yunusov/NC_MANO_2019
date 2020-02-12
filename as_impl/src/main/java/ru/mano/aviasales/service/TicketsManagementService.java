package ru.mano.aviasales.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.mano.aviasales.dto.CityDto;
import ru.mano.aviasales.dto.TicketDto;
import ru.mano.aviasales.entity.Ticket;
import ru.mano.aviasales.mapper.CityMapper;
import ru.mano.aviasales.mapper.TicketMapper;
import ru.mano.aviasales.repository.TicketRepository;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class TicketsManagementService {

    @Autowired
    private TicketRepository ticketRepository;
    private static long nextId = 0;

    public TicketDto createTicket(CityDto source, CityDto destination) {
        long id = generateNewId();
        Ticket t = new Ticket(id, CityMapper.mapTo(source), CityMapper.mapTo(destination));
        ticketRepository.save(t);
        return TicketMapper.mapTo(t);
    }

    public TicketDto updateTicket(long id, CityDto newSource, CityDto newDestination) throws Exception {
        Ticket ticket = ticketRepository.getOne(id);
        ticket.setSource(CityMapper.mapTo(newSource));
        ticket.setDestination(CityMapper.mapTo(newDestination));
        ticketRepository.save(ticket);
        return TicketMapper.mapTo(ticket);
    }

    public List<TicketDto> updateTickets(CityDto source, CityDto destination, CityDto newSource, CityDto newDestination) {
        List<Ticket> tickets =
                ticketRepository.findBySourceAndDestination(
                        CityMapper.mapTo(source),
                        CityMapper.mapTo(destination));

        tickets.forEach(ticket -> {
            ticket.setDestination(CityMapper.mapTo(newDestination));
            ticket.setSource(CityMapper.mapTo(newSource));
        });

        ticketRepository.saveAll(tickets);
        return tickets.stream().map(TicketMapper::mapTo).collect(Collectors.toList());
    }

    public List<TicketDto> updateTickets(List<Long> ids, CityDto newSource, CityDto newDestination) {
        List<Ticket> tickets = ticketRepository.findAllById(ids);

        tickets.forEach(ticket -> {
            ticket.setDestination(CityMapper.mapTo(newDestination));
            ticket.setSource(CityMapper.mapTo(newSource));
        });

        ticketRepository.saveAll(tickets);
        return tickets.stream().map(TicketMapper::mapTo).collect(Collectors.toList());
    }

    public List<TicketDto> getTickets(CityDto source, CityDto destination) {
        return ticketRepository.findBySourceAndDestination(CityMapper.mapTo(source), CityMapper.mapTo(destination))
                .stream().map(TicketMapper::mapTo)
                .collect(Collectors.toList());
    }

    public TicketDto getTicketById(long id) throws Exception {
        Optional<Ticket> ticket = ticketRepository.findById(id);
        if (ticket.isPresent()) {
            return TicketMapper.mapTo(ticket.get());
        } else {
            throw new Exception("Ticket with id " + id + " not found");
        }
    }

    public void deleteTickets(CityDto source, CityDto destination) {
        ticketRepository.deleteBySourceAndDestination(
                CityMapper.mapTo(source),
                CityMapper.mapTo(destination));
    }

    public void deleteTicketById(long id) {
        ticketRepository.deleteById(id);
    }

    private long generateNewId() {
        return nextId++;
    }

}
