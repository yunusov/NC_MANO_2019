package ru.mano.aviasales.entity;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;


@Data
@Entity
public class CityEntity {
    @Id
    private String id;
    private String name;
    private double x, y;
}


