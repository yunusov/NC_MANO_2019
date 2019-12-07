package ru.mano.aviasales.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.*;
import ru.mano.aviasales.model.Route;
import ru.mano.aviasales.model.Ticket;
import ru.mano.aviasales.model.User;
import ru.mano.aviasales.service.RoutesManagementService;

import java.util.List;

@RestController
@RequestMapping("routes")
public class RouteController {
    private RoutesManagementService routeService = RoutesManagementService.getInstance();

    @PostMapping("create")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "OK")})
    @ApiOperation(value = "createRoute", notes = "RouteController")
    public long createRoute(List<Ticket> tickets, User owner) {
        return routeService.createRoute(tickets, owner);
    }

    @GetMapping("get")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK")})
    @ApiOperation(value = "getRoute", notes = "RouteController")
    public Route getRoute(long id) {
        return routeService.getRoute(id);
    }

    @GetMapping("getUsersRoutes")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK")})
    @ApiOperation(value = "getUsersRoutes", notes = "RouteController")
    public List<Route> getUsersRoutes(User owner) {
        return routeService.getUsersRoutes(owner);
    }

    @PutMapping("addInRoute")
    @ApiResponses(value = {@ApiResponse(code = 204, message = "NO CONTENT")})
    @ApiOperation(value = "addTicketInRoute", notes = "RouteController")
    public void addTicketInRoute(long id, Ticket ticket) {
        routeService.addTicketInRoute(id, ticket);
    }

    @PutMapping("addAtIndex")
    @ApiResponses(value = {@ApiResponse(code = 204, message = "NO CONTENT")})
    @ApiOperation(value = "addTicketAtIndex", notes = "RouteController")
    public void addTicketAtIndex(long id, int index, Ticket ticket) {
        routeService.addTicketAtIndex(id, index, ticket);
    }

    @DeleteMapping("deleteFromRoute")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK")})
    @ApiOperation(value = "deleteTicketFromRoute", notes = "RouteController")
    public void deleteTicketFromRoute(long id, Ticket ticket) {
        routeService.deleteTicketFromRoute(id, ticket);
    }

    @DeleteMapping("deleteRoute")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK")})
    @ApiOperation(value = "deleteRoute", notes = "RouteController")
    public void deleteRoute(long id) {
        routeService.deleteRoute(id);
    }
}
