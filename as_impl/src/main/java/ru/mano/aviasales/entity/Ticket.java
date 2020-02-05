package ru.mano.aviasales.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
//@Table(catalog = "postgres", schema = "aviato", name = "tickets")
public class Ticket {

    @Id
    @Column(name = "ticket_id")
    private long id;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn
    private City source;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn
    private City destination;

    public Ticket() {
    }

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
