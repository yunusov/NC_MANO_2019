package ru.mano.aviasales.dto;


import lombok.Data;

import javax.persistence.Entity;


@Data
public class City extends AbstractEntityParent {
    private String name;
    private double x, y;


    public City(int id, String name, double x, double y) {
        super(id);
        this.name = name;
        this.x = x;
        this.y = y;
    }
}


