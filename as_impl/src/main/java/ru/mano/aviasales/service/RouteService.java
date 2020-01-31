package ru.mano.aviasales.service;

import ru.mano.aviasales.dto.RouteDto;
import ru.mano.aviasales.dto.TicketDto;
import ru.mano.aviasales.dto.UserDto;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class RouteService {
    private List<RouteDto> storage = new ArrayList<>();
    private static RouteService routeService;
    private TicketService ticketService = TicketService.getInstance();
    private UserService userService = UserService.getInstance();
    private static int nextId = 0;

    static {
        routeService = new RouteService();
    }

    private RouteService(){
    }

    public static RouteService getInstance(){
        return routeService;
    }


    public RouteDto getRoute(int id) {
        try {
            return storage.stream()
                    .filter(t -> t.getId() == id)
                    .findAny()
                    .orElseThrow(NoSuchElementException::new);
        } catch (NoSuchElementException e) {
            System.out.println( "Can\'t get Route with id: " + id + '\n' + e.getMessage());
            return null;
        }
    }




    public RouteDto createRoute(int userId, int ticketId) {
        UserDto userDto = userService.getUser(userId);
        if(userDto == null) {
            System.out.println("Cant\'t create Route. There is no user with id " + userId);
            return null;
        }

        TicketDto ticketDto = ticketService.getTicket(ticketId);
        if (ticketDto == null) {
            System.out.println("Cant\'t create Route. There is no ticket with id " + ticketId);
            return null;
        }

        RouteDto routeDto = new RouteDto(generateNewId(), userDto, ticketDto);

        if (storage.add(routeDto) )
            return routeDto;
        else
            System.out.println( "Can\'t add in storage new Route " + routeDto);
        return null;
    }




    public RouteDto createRoute(int userId, int... ticketIds) {
        UserDto userDto = userService.getUser(userId);
        if(userDto == null) {
            System.out.println("Cant\'t create Route. There is no user with id " + userId);
            return null;
        }

        RouteDto routeDto = new RouteDto(generateNewId(), userDto);
        for (int ticket : ticketIds) {
            TicketDto tempTicketDto = ticketService.getTicket(ticket);
            if (tempTicketDto == null) {
                System.out.println("Cant\'t create Route. There is no ticket with id " + ticket);
                return null;
            }
            routeDto.addTicket(tempTicketDto);
        }
        if (storage.add(routeDto) )
            return routeDto;
        else
            System.out.println( "Can\'t add in storage new Route " + routeDto);
        return null;
    }



    public RouteDto updateRouteUser(int id, int userId) {
        RouteDto routeDto = getRoute(id);
        if (routeDto == null) {
            System.out.println("Cant\'t change Route. There is no Route with id " + id);
            return null;
        }

        UserDto userDto = userService.getUser(userId);
        if(userDto == null) {
            System.out.println("Cant\'t change Route. There is no User with id " + userId);
            return null;
        }

        routeDto.setUserDtoId(userDto);
        return routeDto;
    }

    public RouteDto updateRouteTickets(int id, int index, int newTicketId) {
        RouteDto routeDto = getRoute(id);
        if (routeDto == null) {
            System.out.println("Cant\'t change Route. There is no Route with id " + id);
            return null;
        }
        if (routeDto.getTicketList().size() < index) {
            System.out.println("Cant\'t change Route. There is no ticket with index " + index);
            return null;
        }
        TicketDto ticketDto = ticketService.getTicket(newTicketId);
        if (ticketDto == null) {
            System.out.println("Cant\'t change Route. There is no ticket with id " + newTicketId);
            return null;
        }
        routeDto.getTicketList().set(index, ticketDto);

        return routeDto;
    }

    public RouteDto deleteRoute(int id) {
        RouteDto routeDto = getRoute(id);
        if (routeDto == null) {
            System.out.println("Can\'t complete deletion, because Route with id " + id + " does not exists");
            return null;
        } else if(!storage.remove(routeDto)) {
            System.out.println("Can\'t complete deletion of existing Route ");
            return null;
        }
        return routeDto;
    }

    public double getRouteDistanceById(int routeId) {
        RouteDto routeDto = getRoute(routeId);
        if (routeDto == null) {
            System.out.println("Can\'t compute distance, because Route with id " + routeId + " does not exists");
            return 0.0;
        }
        return totalDistance(routeDto);
    }

    public double getRouteCostById(int routeId) {
        RouteDto routeDto = getRoute(routeId);
        if (routeDto == null) {
            System.out.println("Can\'t compute total cost, because Route with id " + routeId + " does not exists");
            return 0.0;
        }
        return totalCost(routeDto);
    }
    private int generateNewId() {
        return nextId++;
    }

    private double totalCost(RouteDto routeDto) {
        return routeDto.getTicketDtos().stream()
                                 .mapToDouble(TicketDto::getCost)
                                 .sum();
    }
    private double totalDistance(RouteDto routeDto) {
        return routeDto.getTicketDtos().stream()
                .mapToDouble(ticketService::getDistance)
                .sum();
    }

}
