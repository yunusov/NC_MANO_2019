package ru.mano.aviasales.entity;


import lombok.Data;


@Data
public class City extends AbstractEntityParent {
    private String name;
    private int x, y;


    public City(int id, String name, int x, int y) {
        super(id);
        this.name = name;
        this.x = x;
        this.y = y;
    }
}


