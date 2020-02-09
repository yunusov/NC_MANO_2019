package ru.ncedu.project.dto;

import lombok.Data;

@Data
public class TicketDto extends AbstractEntityParent {
    private CityDto from;
    private CityDto to;
    private double cost;


    public TicketDto(int id, CityDto from, CityDto to, double cost) {
        super(id);
        this.from = from;
        this.to = to;
        this.cost = cost;
    }
}
