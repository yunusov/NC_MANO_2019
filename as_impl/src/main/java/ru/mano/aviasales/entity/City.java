package ru.mano.aviasales.entity;


import lombok.Data;
import org.postgresql.util.SharedTimer;
import ru.mano.aviasales.dto.AbstractEntityParent;

import javax.persistence.Entity;
import javax.persistence.Id;


@Data
@Entity
public class City  {
    @Id
    private int id;
    private String name;
    private double x, y;


    public City(int id, String name, double x, double y) {
        this.id = id;
        this.name = name;
        this.x = x;
        this.y = y;
    }
}


