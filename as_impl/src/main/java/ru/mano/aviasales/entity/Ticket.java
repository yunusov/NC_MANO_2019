package ru.mano.aviasales.entity;

import java.util.Objects;

public class Ticket {
    private City from;
    private City to;
    private double cost;

    public Ticket(City from, City to, double cost) {
        this.from = from;
        this.to = to;
        this.cost = cost;
    }

    public City getFrom() {
        return from;
    }

    public void setFrom(City from) {
        this.from = from;
    }

    public City getTo() {
        return to;
    }

    public void setTo(City to) {
        this.to = to;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "from=" + from +
                ", to=" + to +
                ", cost=" + cost +
                '}';
    }

    @Override
    public boolean equals(Object other) {
        if (this == other)
            return true;
        if (other == null || getClass() != other.getClass()) return false;
        Ticket ticket = (Ticket) other;
        return Double.compare(ticket.cost, cost) == 0 &&
                from.equals(ticket.from) &&
                to.equals(ticket.to);
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, to, cost);
    }
}
