package ru.mano.aviasales.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.mano.aviasales.dto.RouteDto;
import ru.mano.aviasales.dto.TicketDto;
import ru.mano.aviasales.dto.UserDto;
import ru.mano.aviasales.entity.Route;
import ru.mano.aviasales.mapper.RouteMapper;
import ru.mano.aviasales.mapper.TicketMapper;
import ru.mano.aviasales.mapper.UserMapper;
import ru.mano.aviasales.repository.RouteRepository;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class RoutesManagementService {

    @Autowired
    RouteRepository routeRepository;

    private static long nextId;

    public RouteDto createRoute(List<TicketDto> list, UserDto owner) {
        long id = generateNewId();
        RouteDto route = new RouteDto(id, list, owner);
        routeRepository.save(RouteMapper.mapTo(route));
        return route;
    }

    public RouteDto getRoute(long id) {
        return RouteMapper.mapTo(routeRepository.findById(id).orElse(null));
    }

    public List<RouteDto> getUsersRoutes(UserDto owner) {
        return routeRepository.findByUser(UserMapper.mapTo(owner))
                .stream().map(RouteMapper::mapTo)
                .collect(Collectors.toList());
    }

    public void addTicketInRoute(long id, TicketDto ticket) {
        Optional<Route> route = routeRepository.findById(id);
        route.ifPresent(route1 -> {
            route1.addNextRoot(TicketMapper.mapTo(ticket));
            routeRepository.save(route1);
        });
    }

    public void addTicketAtIndex(long id, int index, TicketDto ticket) {
        Optional<Route> route = routeRepository.findById(id);
        route.ifPresent(route1 -> {
            route1.addRootAtIndex(TicketMapper.mapTo(ticket), index);
            routeRepository.save(route1);
        });
    }

    public void deleteTicketFromRoute(long id, TicketDto ticket) {
        Optional<Route> route = routeRepository.findById(id);
        route.ifPresent(route1 -> {
            route1.deleteRoot(TicketMapper.mapTo(ticket));
            routeRepository.save(route1);
        });
    }

    public void deleteRoute(long id) {
        routeRepository.deleteById(id);
    }

    private long generateNewId() {
        return nextId++;
    }
}
