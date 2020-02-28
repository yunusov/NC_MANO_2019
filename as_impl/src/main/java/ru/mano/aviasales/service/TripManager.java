package ru.mano.aviasales.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.mano.aviasales.entity.RouteEntity;
import ru.mano.aviasales.entity.TicketEntity;
import ru.mano.aviasales.entity.UserEntity;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;


@Component
public class TripManager {

    private final int MAX_HOPS = 4;  // Maybe must come from caller method

    @Autowired
    private TicketService ticketService;

    @Transactional(propagation= Propagation.REQUIRED)
    public List<RouteEntity> searchRoutes(String inputStartCity, String inputEndCity, UserEntity userEntity) {

        List<TicketEntity> totalTickets = ticketService.getAllTickets();
        LinkedList<TripAgent> agentList = new LinkedList<>();
        LinkedList<TripAgent> resultList = new LinkedList<>();

        List<TicketEntity> startTickets = findTicketsWithThisStartByName(totalTickets, inputStartCity);

        for (TicketEntity ticket : startTickets) {
            agentList.add(new TripAgent(ticket));
        }
        startTickets.clear();

        for (int hop = 0; hop < MAX_HOPS; ++hop) {
            nextHop(totalTickets, agentList, resultList, inputEndCity);
        }

        return resultList.stream()
                .map(e -> e.toRouteEntity(userEntity))
                .collect(Collectors.toList());
    }



    private List<TicketEntity> findTicketsWithThisStartByName(List<TicketEntity> totalTickets, String inputStartCity) {
        List<TicketEntity> tickets = totalTickets.stream()
                .filter(e -> e.getFrom().getName().equals(inputStartCity))
                .collect(Collectors.toList());

        return tickets;
    }


    private void nextHop(List<TicketEntity> totalTickets, LinkedList<TripAgent> agentList, LinkedList<TripAgent> resultList, String inputEndCity) {
        LinkedList<TripAgent> bufferList = new LinkedList<>();

        for (TripAgent agent : agentList) {
            TicketEntity lastTicketOfThisAgent = agent.tickets.getLast();

            if (lastTicketOfThisAgent.getTo().getName().equals(inputEndCity)) {
                resultList.add(agent);
            }
            else {
                List<TicketEntity> startTickets = findTicketsWithThisStartByName(totalTickets, lastTicketOfThisAgent.getTo().getName());  //Searching for tickets form current pos

                for (TicketEntity ticket : startTickets) {
                    bufferList.add(new TripAgent(agent, ticket));
                }
            }
        }
        agentList.clear();
        agentList.addAll(bufferList);
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
