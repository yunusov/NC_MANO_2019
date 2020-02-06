package ru.mano.aviasales.service;

import org.springframework.beans.factory.annotation.Autowired;
import ru.mano.aviasales.dto.RouteDto;
import ru.mano.aviasales.dto.TicketDto;
import ru.mano.aviasales.dto.UserDto;
import ru.mano.aviasales.entity.CityEntity;
import ru.mano.aviasales.entity.RouteEntity;
import ru.mano.aviasales.entity.TicketEntity;
import ru.mano.aviasales.mapper.RouteMapper;
import ru.mano.aviasales.mapper.UserMapper;
import ru.mano.aviasales.repository.RouteRepository;
import ru.mano.aviasales.repository.TicketRepository;

import java.util.*;

public class RouteService {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private UserService userService;

    @Autowired
    private RouteMapper routeMapper;

    @Autowired
    private RouteRepository routeRepository;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private UserMapper userMapper;



    public RouteDto getRoute(String id) {
        Optional<RouteEntity> route = routeRepository.findById(id);
        RouteEntity routeEntity = route.orElseThrow(IllegalArgumentException::new);

        return routeMapper.from(routeEntity);
    }




    public RouteDto createRoute(String userId, String ticketId) {
        RouteDto routeDto = new RouteDto(userService.getUser(userId), ticketService.getTicket(ticketId));
        routeRepository.save(routeMapper.from(routeDto));

        return routeDto;
    }



    public RouteDto createRoute(String userId, String... ticketIds) {
        List<TicketEntity> list = ticketRepository.findAllById(Arrays.asList(ticketIds));

        RouteEntity routeEntity = new RouteEntity(userMapper.from(userService.getUser(userId)), list);
        routeRepository.save(routeEntity);

        return routeMapper.from(routeEntity);
    }


    public RouteDto updateRoute(String routeId, RouteDto  newRoute) {
        if(routeId != null && routeRepository.existsById(routeId) ) {
            newRoute.setId(routeId);
            routeRepository.deleteById(routeId);
            routeRepository.save(routeMapper.from(newRoute));

            return newRoute;
        } else
            throw new IllegalArgumentException("Can\'t update route with id " + routeId);
    }

    public RouteDto deleteRoute(String routeId) {
        if(routeId != null) {
            RouteDto deleted = getRoute(routeId);
            routeRepository.deleteById(routeId);
            return deleted;
        } else
            return null;
    }

    public double getRouteDistanceById(String routeId) {
        RouteDto routeDto = getRoute(routeId);

        return totalDistance(routeDto);
    }

    public double getRouteCostById(String routeId) {
        RouteDto routeDto = getRoute(routeId);

        return totalCost(routeDto);
    }

    private double totalCost(RouteDto routeDto) {
        return routeDto.getTickets().stream()
                                 .mapToDouble(TicketDto::getCost)
                                 .sum();
    }
    private double totalDistance(RouteDto routeDto) {
        return routeDto.getTickets().stream()
                .mapToDouble(ticketService::getDistance)
                .sum();
    }

}
