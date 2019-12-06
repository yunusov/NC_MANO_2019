package ru.mano.aviasales.model;

import lombok.Getter;
import lombok.Setter;

public class Ticket extends Id {
    @Getter
    @Setter
    private City source;
    @Getter
    @Setter
    private City destination;

    public Ticket(long id, City source, City destination) {
        this.id = id;
        this.source = source;
        this.destination = destination;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ticket ticket = (Ticket) o;

        if (!getSource().equals(ticket.getSource())) return false;
        return getDestination().equals(ticket.getDestination());
    }
}
