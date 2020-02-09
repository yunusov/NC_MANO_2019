package ru.ncedu.project.entity;

import lombok.Data;

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
