package ru.mano.aviasales.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@Data
@Entity
@Table(catalog = "postgres", schema = "aviato", name = "tickets")
public class Ticket {

    @Id
    @Column(name = "ticket_id")
    private long id;

    @ManyToOne
    @JoinColumn
    private City source;

    @ManyToOne
    @JoinColumn
    private City destination;

    public Ticket() {
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
