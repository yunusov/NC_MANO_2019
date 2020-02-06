package ru.mano.aviasales.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.mano.aviasales.entity.CityEntity;


@Data
@AllArgsConstructor
public class TicketDto extends BaseDto {
    private CityDto from;
    private CityDto to;
    private double cost;

    public TicketDto(String id, CityDto from, CityDto to, double cost) {
        super (id);
        this.from = from;
        this.to = to;
        this.cost = cost;
    }


}
