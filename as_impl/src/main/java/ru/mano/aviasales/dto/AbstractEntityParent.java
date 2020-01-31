package ru.mano.aviasales.dto;


import lombok.Data;

import javax.persistence.Id;


@Data
public abstract class AbstractEntityParent {
    int id;

    public AbstractEntityParent(int id) {
        this.id = id;
    }

    //String name;
}
