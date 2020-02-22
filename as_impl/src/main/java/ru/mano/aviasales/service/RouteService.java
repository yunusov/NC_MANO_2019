package ru.mano.aviasales.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
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


@Component
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


    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public RouteDto getRoute(String id) {
        Optional<RouteEntity> route = routeRepository.findById(id);
        RouteEntity routeEntity = route.orElseThrow(IllegalArgumentException::new);

        return routeMapper.from(routeEntity);
    }



    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public RouteDto createRoute(String userId, String ticketId) {
        RouteDto routeDto = new RouteDto(userService.getUser(userId), ticketService.getTicket(ticketId));
        RouteEntity routeEntity = routeRepository.save(routeMapper.from(routeDto));

        return routeMapper.from(routeEntity);
    }


    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public RouteDto createRoute(String userId, String... ticketIds) {
        List<TicketEntity> list = ticketRepository.findAllById(Arrays.asList(ticketIds));

        RouteEntity routeEntity = new RouteEntity(userMapper.from(userService.getUser(userId)), list);
        RouteEntity returnEntity = routeRepository.save(routeEntity);

        return routeMapper.from(returnEntity);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public RouteDto updateRoute(String routeId, RouteDto  newRoute) {
        if(routeId != null && routeRepository.existsById(routeId) ) {
            newRoute.setId(routeId);
            RouteEntity routeEntity = routeRepository.save(routeMapper.from(newRoute));

            return routeMapper.from(routeEntity);
        } else {
            throw new IllegalArgumentException("Can\'t update route with id " + routeId);
        }
    }


    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void deleteRoute(String routeId) {
        if(routeId != null) {
            routeRepository.deleteById(routeId);
        } else {
            throw new IllegalArgumentException("Can\'t delete route");
        }
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public double getRouteDistanceById(String routeId) {
        RouteDto routeDto = getRoute(routeId);

        return totalDistance(routeDto);
    }


    @Transactional(propagation = Propagation.REQUIRES_NEW)
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



    public List<RouteDto> searchRoutes(String inputStartCity, String inputEndCity) {
        List<TicketEntity> all = ticketRepository.findAll();
        List<RouteDto> routeDtos = new ArrayList<>();
        List<RouteEntity> routeEntities = new ArrayList<>();

        for (TicketEntity ticketEntity : all) {
            if (inputStartCity.equals(ticketEntity.getFrom().getName())) {
                RouteEntity routeEntity = new RouteEntity();
                routeEntity.addTicket(ticketEntity);
                routeEntities.add(routeEntity);
            }
        }

        return null;
    }
}
