package ru.mano.aviasales.mapper;

import ru.mano.aviasales.dto.RouteDto;
import ru.mano.aviasales.entity.Route;

import java.util.stream.Collectors;

public class RouteMapper {

    public static Route mapTo(RouteDto dto) {
        Route entity = new Route();
        entity.setId(dto.getId());
        entity.setOwner(UserMapper.mapTo(dto.getOwner()));
        entity.setRoute(dto.getRoute().stream().map(TicketMapper::mapTo).collect(Collectors.toList()));
        return entity;
    }

    public static RouteDto mapTo(Route entity) {
        RouteDto dto = new RouteDto();
        dto.setId(entity.getId());
        dto.setOwner(UserMapper.mapTo(entity.getOwner()));
        dto.setRoute(entity.getRoute().stream().map(TicketMapper::mapTo).collect(Collectors.toList()));
        return dto;
    }
}
