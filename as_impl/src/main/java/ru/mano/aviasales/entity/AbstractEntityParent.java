package ru.mano.aviasales.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
public abstract class AbstractEntityParent {
    int id;

    public AbstractEntityParent(int id) {
        this.id = id;
    }

    //String name;
}
