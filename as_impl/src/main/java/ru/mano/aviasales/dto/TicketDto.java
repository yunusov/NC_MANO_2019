package ru.mano.aviasales.dto;

import lombok.Getter;
import lombok.Setter;

public class TicketDto extends BaseEntityDto {
    @Getter
    @Setter
    private CityDto source;
    @Getter
    @Setter
    private CityDto destination;

    public TicketDto(long id, CityDto source, CityDto destination) {
        this.id = id;
        this.source = source;
        this.destination = destination;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TicketDto ticket = (TicketDto) o;

        if (!getSource().equals(ticket.getSource())) return false;
        return getDestination().equals(ticket.getDestination());
    }
}