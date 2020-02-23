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

    private final int MAX_STEPS = 6;

    @Autowired
    private TicketService ticketService;

    private List<TicketEntity> totalTickets;
    private String inputEndCity;

    private LinkedList<Agent> agentList = new LinkedList<>();  // LinkedList ?
    private LinkedList<Agent> childList = new LinkedList<>();
    private LinkedList<Agent> resultList = new LinkedList<>();


    public List<RouteEntity> searchRoutes(String inputStartCity, String inputEndCity, UserEntity userEntity) {
        this.totalTickets = ticketService.getAllTickets();
        this.inputEndCity = inputEndCity;


        List<TicketEntity> startTickets = findTicketsWithThisStartById(inputStartCity);

        putAgentsOnTheseTickets(startTickets);

        for (int step = 0; step < MAX_STEPS; ++step) {
            nextStep();
        }

        return resultList.stream()
                .map(e -> e.toRouteEntity(userEntity))
                .collect(Collectors.toList());
    }



    private List<TicketEntity> findTicketsWithThisStartById(String inputStartCity) {
        List<TicketEntity> tickets = totalTickets.stream()
                .filter(e -> e.getFrom().getId().equals(inputStartCity))
                .collect(Collectors.toList());

        if (tickets == null || tickets.size() == 0) {
            throw new NoSuchElementException("Can\'t find tickets");
        }
        return tickets;
    }


    private void putAgentsOnTheseTickets (List<TicketEntity> list) {
        for (TicketEntity ticket : list) {
            agentList.add(new Agent(ticket));
        }
    }


    private void nextStep() {
        for (Agent agent : agentList) {
            TicketEntity lastTicketOfThisAgent = agent.tickets.getLast();

            if (lastTicketOfThisAgent.getTo().getId().equals(inputEndCity)) {
                resultList.add(agent);
                continue;
            }

            try {
                List<TicketEntity> startTickets = findTicketsWithThisStartById(lastTicketOfThisAgent.getTo().getId());  //Searching for tickets form current pos

                for (TicketEntity ticket : startTickets) {
                    childList.add(new Agent(agent, ticket));
                }

            }catch (NoSuchElementException e) {
                return;  // This is dead end!
            }
        }
        agentList.clear();

        agentList = childList;
        childList = new LinkedList<>();
    }


    private class Agent {

        private LinkedList<TicketEntity> tickets;


        public Agent(TicketEntity ticketEntity) {
            this.tickets = new LinkedList<>();
            this.tickets.add(ticketEntity);
        }

        public Agent(Agent parent, TicketEntity ticketEntity) {
            this.tickets = new LinkedList<>();
            this.tickets.addAll(parent.tickets);
            this.tickets.add(ticketEntity);
        }

        RouteEntity toRouteEntity(UserEntity userEntity) {
            return new RouteEntity(userEntity, tickets);
        }


    }
}
