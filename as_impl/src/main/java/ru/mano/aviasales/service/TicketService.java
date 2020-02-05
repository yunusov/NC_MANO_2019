package ru.mano.aviasales.service;

import ru.mano.aviasales.dto.CityDto;
import ru.mano.aviasales.dto.TicketDto;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class TicketService {

    private List<TicketDto> storage = new ArrayList<>();
    private static TicketService ticketService;
    private CityServiceLegacy cityService = CityServiceLegacy.getInstance();
    private static int nextId = 0;

    static {
        ticketService = new TicketService();
    }

    private TicketService(){
    }

    public static TicketService getInstance(){
        return ticketService;
    }


    public TicketDto getTicket(int id) {
        try {
            return storage.stream()
                    .filter(u -> u.getId() == id)
                    .findAny()
                    .orElseThrow(NoSuchElementException::new);
        } catch (NoSuchElementException e) {
            System.out.println( "Can\'t get Ticket with id: " + id + '\n' + e.getMessage());
            return null;
        }
    }

    public TicketDto createTicket(int fromId, int toId, double cost) {
        TicketDto ticketDto = new TicketDto(generateNewId(), cityService.getCity(fromId), cityService.getCity(toId), cost);
        if (storage.add(ticketDto) )
            return ticketDto;
        else
            System.out.println( "Can\'t add in storage new Ticket " + ticketDto);
        return null;

    }

    public TicketDto createTicket(CityDto from, CityDto to, double cost) {
        TicketDto ticketDto = new TicketDto(generateNewId(), from, to, cost);
        if (storage.add(ticketDto) )
            return ticketDto;
        System.out.println( "Can\'t add in storage new Ticket " + ticketDto);
        return null;
    }

    public TicketDto updateTicket(int id, TicketDto ticketDto) {
        // TODO: checking
        return storage.set(id, ticketDto);
    }

    @Deprecated
    public TicketDto updateTicketCost(int id, double cost) {
        TicketDto ticketDto = getTicket(id);
        if (ticketDto != null) {
            ticketDto.setCost(cost);
            return ticketDto;
        }
        System.out.println("Can\'t change cost. There is no Ticket with id " + id);
        return null;
    }


    @Deprecated
    public TicketDto updateTicketFrom(int id, int fromId) {
        TicketDto ticketDto = getTicket(id);
        if (ticketDto != null) {
            ticketDto.setFrom(cityService.getCity(fromId));
            return ticketDto;
        }
        System.out.println("Can\'t change City from.There is no Ticket with id " + id);
        return null;
    }

    @Deprecated
    public TicketDto updateTicketTo(int id, int toId) {
        TicketDto ticketDto = getTicket(id);
        if (ticketDto != null) {
            ticketDto.setTo(cityService.getCity(toId));
            return ticketDto;
        }
        System.out.println("Can\'t change City from.There is no Ticket with id " + id);
        return null;
    }

    public TicketDto deleteTicket(int id) {
        TicketDto ticketDto = getTicket(id);
        if (ticketDto == null) {
            System.out.println("Can\'t complete deletion, because Ticket with id " + id + " does not exists");
            return null;
        } else if(!storage.remove(ticketDto)) {
            System.out.println("Can\'t complete deletion of existing Ticket ");
            return null;
        }
        return ticketDto;
    }

    public double getTicketDistanceById(int ticketId) {
        TicketDto ticketDto = getTicket(ticketId);
        if (ticketDto == null) {
            System.out.println("Can\'t compute distance, because Ticket with id " + ticketId + " does not exists");
            return 0.0;
        }
        return getDistance(ticketDto);
    }

    private int generateNewId() {
        return nextId++;
    }


    public double getDistance (TicketDto ticketDto) {
        double resultX = ticketDto.getFrom().getX() - ticketDto.getTo().getX();
        double resultY = ticketDto.getFrom().getY() - ticketDto.getTo().getY();
        return Math.sqrt(resultX * resultX + resultY * resultY);
    }
}
