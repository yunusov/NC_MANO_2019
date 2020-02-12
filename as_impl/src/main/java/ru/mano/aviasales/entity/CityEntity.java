package ru.mano.aviasales.entity;


import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;


@Data
@Entity
@AllArgsConstructor
public class CityEntity {
    @Id
    private String id;
    private String name;
    private double x, y;

    public CityEntity() {
    }
}


