package ru.mano.aviasales.entity;


import lombok.Data;


@Data
public abstract class AbstractEntityParent {
    int id;

    public AbstractEntityParent(int id) {
        this.id = id;
    }

    //String name;
}
