package ru.mano.aviasales.service;

import ru.mano.aviasales.model.City;
import ru.mano.aviasales.model.Ticket;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

//TODO: make as singleton
public class TicketsManagementService {

    private static List<Ticket> ticketsStorage = new LinkedList<>();
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

    public void createTicket(City source, City destination) {
        ticketsStorage.add(new Ticket(generateNewId(), source, destination));
    }

    public void updateTicket(long id, City newSource, City newDestination) {
        Ticket ticket = getTicketById(id);
        ticket.setSource(newSource);
        ticket.setDestination(newDestination);
    }

    public void updateTickets(City source, City destination, City newSource, City newDestination) {
        //1 способ
        List<Ticket> list = getTickets(source, destination);
        for (Ticket t : list) {
            t.setSource(newSource);
            t.setDestination(newDestination);
        }

        //2 способ
        /*Ticket ticket = new Ticket(0, source, destination);
        ticketsStorage.forEach(t -> {
            if (t.equals(ticket)) {
                t.setSource(newSource);
                t.setDestination(newDestination);
            }
        });*/
    }

    public void updateTickets(List<Long> ids, City newSource, City newDestination) {
        ticketsStorage.forEach(ticket -> {
            if (ids.contains(ticket.getId())) {
                ticket.setSource(newSource);
                ticket.setDestination(newDestination);
            }
        });
    }

    public List<Ticket> getTickets(City source, City destination) {
        //1 способ
        return ticketsStorage.stream()
                .filter(t ->
                        t.getDestination().equals(destination) &&
                                t.getSource().equals(source))
                .collect(Collectors.toList());
        //2 способ
        /*Ticket ticket = new Ticket(source, destination);
        return ticketsStorage.stream()
                .filter(t -> t.equals(ticket))
                .collect(Collectors.toList());*/
    }

    public Ticket getTicketById(long id) {
        return ticketsStorage.stream()
                .filter(t ->
                        t.getId() == id)
                .findAny().get();
    }

    public void deleteTickets(City source, City destination) {
        Iterator<Ticket> it = ticketsStorage.iterator();
        Ticket ticket = new Ticket(0, source, destination);
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
