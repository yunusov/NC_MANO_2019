package ru.mano.aviasales.dto;

import lombok.Data;

@Data
public class CityDto extends BaseEntityDto {

    private double x;
    private double y;
    private String name;

    public CityDto(long id, double x, double y, String name) {
        this.x = x;
        this.y = y;
        this.name = name;
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
