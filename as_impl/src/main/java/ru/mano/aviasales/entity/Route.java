package ru.mano.aviasales.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import ru.mano.aviasales.dto.RouteDto;

import javax.persistence.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Data
@AllArgsConstructor
@Entity
@Table(catalog = "postgres", schema = "aviato", name = "routes")
public class Route {

    @Id
    //@GeneratedValue
    private long id;

    @OneToMany
    @ElementCollection(targetClass = Ticket.class)
    @Column
    private List<Ticket> route = new LinkedList<>();

    @OneToOne
    @JoinColumn
    private User owner;

    public Route() {
    }

    public List<Ticket> getRoute() {
        return new LinkedList<>(route);
    }

    public boolean addNextRoot(Ticket newRoot) {
        return route.add(newRoot);
    }

    public void addRootAtIndex(Ticket newRoot, int index) {
        route.add(index, newRoot);
    }

    public void deleteRoot(Ticket ticket) {
        route.removeIf(t -> t.equals(ticket));
    }

    public long getId() {
        return id;
    }
}
