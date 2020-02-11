package ru.mano.aviasales.entity;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@Entity
@Table(catalog = "postgres", schema = "aviato", name = "cities")
public class City {

    @Id
    @GeneratedValue
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

    public City() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
