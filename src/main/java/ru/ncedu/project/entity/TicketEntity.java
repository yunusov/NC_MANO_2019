package ru.ncedu.project.entity;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class TicketEntity {
    @Id
    private int id;
    private CityEntity from;
    private CityEntity to;
    private double cost;


    public TicketEntity(int id, CityEntity from, CityEntity to, double cost) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.cost = cost;
    }
}