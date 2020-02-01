package ru.mano.aviasales.dto;

import lombok.Getter;
import lombok.Setter;

public class CityDto extends BaseEntityDto {
    @Getter
    @Setter
    private double x;
    @Getter
    @Setter
    private double y;
    @Getter
    @Setter
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
