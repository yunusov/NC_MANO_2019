package ru.mano.aviasales.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
//@Table(catalog = "postgres", schema = "aviato", name = "cities")
public class City {

    @Id
    private long id;

    @Getter
    @Setter
    @Column
    private double x;

    @Getter
    @Setter
    @Column
    private double y;

    @Getter
    @Setter
    @Column
    private String name;

    public City() {}

    public City(long id, double x, double y, String name) {
        this.x = x;
        this.y = y;
        this.name = name;
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
