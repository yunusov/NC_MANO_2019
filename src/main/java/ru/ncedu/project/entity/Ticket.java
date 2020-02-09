package ru.ncedu.project.entity;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Ticket {
    @Id
    private int id;
    private City from;
    private City to;
    private double cost;


    public Ticket(int id, City from, City to, double cost) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.cost = cost;
    }
}