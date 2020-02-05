package ru.mano.aviasales.dto;

import lombok.Data;


@Data
public class TicketDto extends BaseDto {
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
