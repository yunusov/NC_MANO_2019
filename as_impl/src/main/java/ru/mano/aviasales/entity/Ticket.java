package ru.mano.aviasales.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.Objects;



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
