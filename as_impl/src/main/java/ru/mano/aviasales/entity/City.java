package ru.mano.aviasales.entity;

import lombok.Data;

import java.util.Objects;

@Data
public class City {
    private String name;
    private int x, y;

    public City(String name, int x, int y) {
        this.name = name;
        this.x = x;
        this.y = y;
    }
}


