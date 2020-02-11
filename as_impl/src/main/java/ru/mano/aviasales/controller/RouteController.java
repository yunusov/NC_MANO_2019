package ru.mano.aviasales.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.mano.aviasales.dto.RouteDto;
import ru.mano.aviasales.dto.TicketDto;
import ru.mano.aviasales.dto.UserDto;
import ru.mano.aviasales.service.RoutesManagementService;

import java.util.List;

@RestController
@RequestMapping("routes")
public class RouteController {

    @Autowired
    private RoutesManagementService routeService;

    @PostMapping("create")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "OK")})
    @ApiOperation(value = "createRoute", notes = "RouteController")
    public RouteDto createRoute(List<TicketDto> tickets, UserDto owner) {
        return routeService.createRoute(tickets, owner);
    }

    @GetMapping("get")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK")})
    @ApiOperation(value = "getRoute", notes = "RouteController")
    public RouteDto getRoute(long id) {
        return routeService.getRoute(id);
    }

    @GetMapping("getUsersRoutes")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK")})
    @ApiOperation(value = "getUsersRoutes", notes = "RouteController")
    public List<RouteDto> getUsersRoutes(UserDto owner) {
        return routeService.getUsersRoutes(owner);
    }

    @PutMapping("addInRoute")
    @ApiResponses(value = {@ApiResponse(code = 204, message = "NO CONTENT")})
    @ApiOperation(value = "addTicketInRoute", notes = "RouteController")
    public void addTicketInRoute(long id, TicketDto ticket) {
        routeService.addTicketInRoute(id, ticket);
    }

    @PutMapping("addAtIndex")
    @ApiResponses(value = {@ApiResponse(code = 204, message = "NO CONTENT")})
    @ApiOperation(value = "addTicketAtIndex", notes = "RouteController")
    public void addTicketAtIndex(long id, int index, TicketDto ticket) {
        routeService.addTicketAtIndex(id, index, ticket);
    }

    @DeleteMapping("deleteFromRoute")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK")})
    @ApiOperation(value = "deleteTicketFromRoute", notes = "RouteController")
    public void deleteTicketFromRoute(long id, TicketDto ticket) {
        routeService.deleteTicketFromRoute(id, ticket);
    }

    @DeleteMapping("deleteRoute")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK")})
    @ApiOperation(value = "deleteRoute", notes = "RouteController")
    public void deleteRoute(long id) {
        routeService.deleteRoute(id);
    }
}
