package ru.mano.aviasales.dto;

import lombok.Data;

import javax.persistence.Entity;


@Data
public class Ticket extends AbstractEntityParent {
    private City from;
    private City to;
    private double cost;


    public Ticket(int id, City from, City to, double cost) {
        super(id);
        this.from = from;
        this.to = to;
        this.cost = cost;
    }
}
