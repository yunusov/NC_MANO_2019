package ru.mano.aviasales.dto;


import lombok.Data;


@Data
public class CityDto extends BaseDto {
    private String name;
    private double x, y;


    public CityDto(int id, String name, double x, double y) {
        super(id);
        this.name = name;
        this.x = x;
        this.y = y;
    }
}


