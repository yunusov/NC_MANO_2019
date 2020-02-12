package ru.mano.aviasales.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Data
@Entity
@AllArgsConstructor
public class TicketEntity {
    @Id
    private String id;
    @ManyToOne
    private CityEntity from;
    @ManyToOne
    private CityEntity to;
    private double cost;

    public TicketEntity() {
    }
/*
    public TicketEntity(String id, CityEntity from, CityEntity to, double cost) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.cost = cost;
    }*/
}
