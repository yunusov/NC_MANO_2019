package ru.mano.aviasales.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Id;
import java.util.UUID;


@Data
@AllArgsConstructor
public abstract class BaseDto {
    private String id;

    public BaseDto() {
         id = UUID.randomUUID().toString();
    }
/*
    public BaseDto(String id) {
        this.id = id;
    }
    */

}
