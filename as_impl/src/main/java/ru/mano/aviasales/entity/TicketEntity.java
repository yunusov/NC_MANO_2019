package ru.mano.aviasales.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Data
@Entity
public class TicketEntity {
    @Id
    private int id;
    @ManyToOne
    private CityEntity from;
    @ManyToOne
    private CityEntity to;
    private double cost;

    public TicketEntity() {
    }

    public TicketEntity(int id, CityEntity from, CityEntity to, double cost) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.cost = cost;
    }
}
