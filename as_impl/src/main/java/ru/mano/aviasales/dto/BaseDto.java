package ru.mano.aviasales.dto;


import lombok.Data;

import javax.persistence.Id;


@Data
public abstract class BaseDto {
    int id;

    public BaseDto(int id) {
        this.id = id;
    }

    //String name;
}
