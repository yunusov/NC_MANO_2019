package ru.mano.aviasales.entity;

import java.util.ArrayList;
import java.util.List;

public class Route {
    private int userId;
    private List<Ticket> tickets;
    /*
    public Route(int userId) {
        this.tickets = tickets;
        this.userId = userId;
    }*/

    private Route() {
        tickets = new ArrayList<>();
    }

    public static Builder makeRoute() {
        return new Route().new Builder();
    }
//*************** Inner class ****************
    public class Builder {
        private Builder() {
        }

        public Builder setUserId(int userId) {
            Route.this.userId = userId;

            return this;
        }
        public Builder addTicket(Ticket ticket) {
            Route.this.tickets.add(ticket);

            return this;
        }
        public Route build() {
            return Route.this;
        }
    }
    //****************************************
    public List<Ticket> getTickets() {
        return new ArrayList<Ticket>(tickets);
        //return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public double totalCost() {  // возможно стоит перенести в Service
        double result = 0;
        for (Ticket ticket : tickets) {
            result += ticket.getCost();
        }
        return result;
    }
    public double totalDistance() {  // возможно стоит перенести в Service
        return tickets.stream()
                      .mapToDouble(Ticket::distanceBetween)
                      .sum();
    }
    public double totalDistance2() {  // возможно стоит перенести в Service
        double result = 0;
        for (Ticket ticket : tickets) {
            result += ticket.distanceBetween();
        }
        return result;
    }

}
