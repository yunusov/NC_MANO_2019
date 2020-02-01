package ru.mano.aviasales.service;

import ru.mano.aviasales.dto.CityDto;
import ru.mano.aviasales.dto.TicketDto;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class TicketsManagementService {

    private static List<TicketDto> ticketsStorage = new LinkedList<>();
    private static long nextId = 0;
    private static TicketsManagementService instance;

    static {
        instance = new TicketsManagementService();
    }

    private TicketsManagementService() {
    }

    public static TicketsManagementService getInstance() {
        return instance;
    }

    public long createTicket(CityDto source, CityDto destination) {
        long id = generateNewId();
        ticketsStorage.add(new TicketDto(id, source, destination));
        return id;
    }

    public void updateTicket(long id, CityDto newSource, CityDto newDestination) {
        TicketDto ticket = getTicketById(id);
        ticket.setSource(newSource);
        ticket.setDestination(newDestination);
    }

    public void updateTickets(CityDto source, CityDto destination, CityDto newSource, CityDto newDestination) {
        //1 способ
        List<TicketDto> list = getTickets(source, destination);
        for (TicketDto t : list) {
            t.setSource(newSource);
            t.setDestination(newDestination);
        }

        //2 способ
        /*TicketDto ticket = new TicketDto(0, source, destination);
        ticketsStorage.forEach(t -> {
            if (t.equals(ticket)) {
                t.setSource(newSource);
                t.setDestination(newDestination);
            }
        });*/
    }

    public void updateTickets(List<Long> ids, CityDto newSource, CityDto newDestination) {
        ticketsStorage.forEach(ticket -> {
            if (ids.contains(ticket.getId())) {
                ticket.setSource(newSource);
                ticket.setDestination(newDestination);
            }
        });
    }

    public List<TicketDto> getTickets(CityDto source, CityDto destination) {
        //1 способ
        return ticketsStorage.stream()
                .filter(t ->
                        t.getDestination().equals(destination) &&
                                t.getSource().equals(source))
                .collect(Collectors.toList());
        //2 способ
        /*TicketDto ticket = new TicketDto(source, destination);
        return ticketsStorage.stream()
                .filter(t -> t.equals(ticket))
                .collect(Collectors.toList());*/
    }

    public TicketDto getTicketById(long id) {
        return ticketsStorage.stream()
                .filter(t ->
                        t.getId() == id)
                .findAny().get();
    }

    public void deleteTickets(CityDto source, CityDto destination) {
        Iterator<TicketDto> it = ticketsStorage.iterator();
        TicketDto ticket = new TicketDto(0, source, destination);
        while (it.hasNext()) {
            if (it.next().equals(ticket)) {
                it.remove();
            }
        }
    }

    public void deleteTicketsById(long id) {
        ticketsStorage.removeIf(ticket -> ticket.getId() == id);
    }

    private long generateNewId() {
        return nextId++;
    }

}
