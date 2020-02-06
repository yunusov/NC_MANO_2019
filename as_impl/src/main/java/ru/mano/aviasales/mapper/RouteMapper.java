package ru.mano.aviasales.mapper;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.mano.aviasales.dto.RouteDto;
import ru.mano.aviasales.entity.RouteEntity;

import java.util.stream.Collectors;

@Component
public class RouteMapper {

    @Autowired
    private TicketMapper ticketMapper;
    @Autowired
    private UserMapper userMapper;

    public RouteDto from (RouteEntity routeEntity) {
        return new RouteDto(
                routeEntity.getId(),
                userMapper.from(routeEntity.getUserEntity()),
                routeEntity.getTickets().stream().map(ticketMapper::from).collect(Collectors.toList())

        );
    }

    public RouteEntity from (RouteDto routeDto) {
        return new RouteEntity(
                routeDto.getId(),
                userMapper.from(routeDto.getUserDtoId()),
                routeDto.getTickets().stream().map(ticketMapper::from).collect(Collectors.toList())

        );
    }



}
