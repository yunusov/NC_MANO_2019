package ru.mano.aviasales.dto;

import lombok.Data;

@Data
public class TicketDto extends BaseEntityDto {

    private CityDto source;
    private CityDto destination;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public TicketDto(long id, CityDto source, CityDto destination) {
        super(id);
        this.source = source;
        this.destination = destination;
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
