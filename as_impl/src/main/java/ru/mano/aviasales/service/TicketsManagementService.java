package ru.mano.aviasales.service;

import ru.mano.aviasales.model.City;
import ru.mano.aviasales.model.Ticket;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class TicketsManagementService {

    private static List<Ticket> ticketsStorage = new LinkedList<>();

    public void addTicket(Ticket ticket){
        ticketsStorage.add(ticket);
    }

    public void updateTicket(City source, City destination, City newSource, City newDestination){
        //1 способ
        List<Ticket> list = getTickets(source, destination);
        for (Ticket t : list) {
            t.setSource(newSource);
            t.setDestination(newDestination);
        }

        //2 способ
        Ticket ticket = new Ticket(source, destination);
        ticketsStorage.forEach(t -> {
            if(t.equals(ticket)){
                t.setSource(newSource);
                t.setDestination(newDestination);
            }
        });
    }

    public List<Ticket> getTickets(City source, City destination){
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

    public void deleteTickets(City source, City destination){
        Iterator<Ticket> it = ticketsStorage.iterator();
        Ticket ticket = new Ticket(source, destination);
        while (it.hasNext()){
            if(it.next().equals(ticket)){
                it.remove();
            }
        }
    }

}
