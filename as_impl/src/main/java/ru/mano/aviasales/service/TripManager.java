package ru.mano.aviasales.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.mano.aviasales.entity.RouteEntity;
import ru.mano.aviasales.entity.TicketEntity;
import ru.mano.aviasales.entity.UserEntity;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;


@Component
public class TripManager {

    private final int MAX_HOPS = 6;  // Maybe must come from caller method

    @Autowired
    private TicketService ticketService;

    private List<TicketEntity> totalTickets;
    private String inputEndCity;

    private LinkedList<TripAgent> agentList = new LinkedList<>();  // LinkedList ?
    private LinkedList<TripAgent> bufferList = new LinkedList<>();
    private LinkedList<TripAgent> resultList = new LinkedList<>();


    public List<RouteEntity> searchRoutes(String inputStartCity, String inputEndCity, UserEntity userEntity) {
        this.totalTickets = ticketService.getAllTickets();
        this.inputEndCity = inputEndCity;

        List<TicketEntity> startTickets = findTicketsWithThisStartById(inputStartCity);


        for (TicketEntity ticket : startTickets) {
            agentList.add(new TripAgent(ticket));
        }

        for (int hop = 0; hop < MAX_HOPS; ++hop) {
            nextHop();
        }

        return resultList.stream()
                .map(e -> e.toRouteEntity(userEntity))
                .collect(Collectors.toList());
    }



    private List<TicketEntity> findTicketsWithThisStartById(String inputStartCity) {
        List<TicketEntity> tickets = totalTickets.stream()
                .filter(e -> e.getFrom().getId().equals(inputStartCity))
                .collect(Collectors.toList());

        if (tickets.isEmpty()) {
            throw new NoSuchElementException("Can\'t find tickets");
        }
        return tickets;
    }


    private void nextHop() {
        for (TripAgent agent : agentList) {
            TicketEntity lastTicketOfThisAgent = agent.tickets.getLast();

            if (lastTicketOfThisAgent.getTo().getId().equals(inputEndCity)) {
                resultList.add(agent);
            }
            else {

                    try {
                        List<TicketEntity> startTickets = findTicketsWithThisStartById(lastTicketOfThisAgent.getTo().getId());  //Searching for tickets form current pos

                        for (TicketEntity ticket : startTickets) {
                            bufferList.add(new TripAgent(agent, ticket));
                        }

                    } catch (NoSuchElementException e) {
                        // This is dead end!
                        // TODO: delete this line later
                        System.out.println("I stopped in city " + lastTicketOfThisAgent.getTo().getName() + " Can\'t go ahead.");
                    }
            }
        }
        agentList.clear();

        agentList = bufferList;
        bufferList = new LinkedList<>();
    }


    private static class TripAgent {   // just ValueHolder as RouteEntity, but without id and user

        private LinkedList<TicketEntity> tickets;

        TripAgent(TicketEntity ticketEntity) {
            this.tickets = new LinkedList<>();
            this.tickets.add(ticketEntity);
        }

        TripAgent(TripAgent parent, TicketEntity ticketEntity) {
            this.tickets = new LinkedList<>();
            this.tickets.addAll(parent.tickets);
            this.tickets.add(ticketEntity);
        }

        RouteEntity toRouteEntity(UserEntity userEntity) {
            return new RouteEntity(userEntity, tickets);
        }
    }
}
