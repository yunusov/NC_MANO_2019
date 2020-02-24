package ru.mano.aviasales.entity;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@Data
@Entity
@Table(catalog = "postgres", schema = "aviato", name = "cities")
public class City {

    @Id
    @GeneratedValue
    private long id;

    @Column
    private double x;

    @Column
    private double y;

    @Column
    private String name;

    public City() {
    }

    public City(double x, double y, String name) {
        this.x = x;
        this.y = y;
        this.name = name;
    }
}
