package ru.mano.aviasales.dto;


import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class CityDto extends BaseDto {
    private String name;
    private double x, y;

    public CityDto(String id, String name, double x, double y) {
        super(id);
        this.name = name;
        this.x = x;
        this.y = y;
    }

}


