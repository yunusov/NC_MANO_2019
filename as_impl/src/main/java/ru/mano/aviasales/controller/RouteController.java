package ru.mano.aviasales.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.*;
import ru.mano.aviasales.entity.Route;
import ru.mano.aviasales.service.RouteService;

@RestController
public class RouteController {

    private RouteService routeService = RouteService.getInstance();


    @PostMapping("/route")
    @ApiResponses(value = {@ApiResponse(code = 202, message = "Created")})
    @ApiOperation(value = "createRoute", notes = "Route Controller")
    public Route createRoute(int userId, int... ticketIds)  {
        return routeService.createRoute( userId, ticketIds);
    }

    @GetMapping("/route")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK")})
    @ApiOperation(value = "getRoute", notes = "Route Controller")
    public Route getRoute(int id) {
            return routeService.getRoute(id);
    }


    @DeleteMapping("/route")
    @ApiResponses(value = {@ApiResponse(code = 204, message = "No Content")})
    @ApiOperation(value = "deleteRoute", notes = "Route Controller")
    public Route deleteRoute(int id) {
        return routeService.deleteRoute(id);
    }


    @PutMapping("/route")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Accepted")})
    @ApiOperation(value = "updateRouteUser", notes = "Route Controller")
    public Route updateRouteUser(int id, @RequestBody Route route) {
        return routeService.updateRouteUser(id, route.getUserId().getId());   //TODO: fox it
        //return routeService.updateRouteTickets(id, index, newTicketId);
    }


    @GetMapping("/route/distance")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK")})
    @ApiOperation(value = "getRouteDistanceById", notes = "Route Controller")
    public double getRouteDistanceById(int id) {
        return routeService.getRouteDistanceById(id);
    }

    @GetMapping("/route/cost")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK")})
    @ApiOperation(value = "getRouteCostById", notes = "Route Controller")
    public double getRouteCostById(int id) {
        return routeService.getRouteCostById(id);
    }
}

/*

    @PostMapping("/route")
    @ApiResponses(value = {@ApiResponse(code = 202, message = "Created")})
    @ApiOperation(value = "createRoute", notes = "Route Controller")
    public Route createRoute(int userId, int ticketId)  {
        return routeService.createRoute( userId, ticketId);
    }

    @PutMapping("/route")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Accepted")})
    @ApiOperation(value = "updateRouteUser", notes = "Route Controller")
    public Route updateRouteUser(int id, int userId) {
        return routeService.updateRouteUser(id, userId);
    }

    @PutMapping("/route")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Accepted")})
    @ApiOperation(value = "updateRouteTickets", notes = "Route Controller")
    public Route updateRouteTickets(int id, int index, int newTicketId) {
        return routeService.updateRouteTickets(id, index, newTicketId);
    }
 */