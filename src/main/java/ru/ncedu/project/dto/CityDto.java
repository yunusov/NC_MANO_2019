package ru.ncedu.project.dto;

import lombok.Data;

@Data
public class CityDto extends AbstractEntityParent {
    private String name;
    private double x, y;


    public CityDto(int id, String name, double x, double y) {
        super(id);
        this.name = name;
        this.x = x;
        this.y = y;
    }
}


