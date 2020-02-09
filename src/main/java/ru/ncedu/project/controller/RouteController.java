package ru.ncedu.project.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.*;
import ru.ncedu.project.dto.RouteDto;
import ru.ncedu.project.service.RouteService;

@RestController
public class RouteController {

    private RouteService routeService = RouteService.getInstance();


    @PostMapping("/route")
    @ApiResponses(value = {@ApiResponse(code = 202, message = "Created")})
    @ApiOperation(value = "createRoute", notes = "Route Controller")
    public RouteDto createRoute(int userId, int... ticketIds)  {
        return routeService.createRoute( userId, ticketIds);
    }

    @GetMapping("/route")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK")})
    @ApiOperation(value = "getRoute", notes = "Route Controller")
    public RouteDto getRoute(int id) {
            return routeService.getRoute(id);
    }


    @DeleteMapping("/route")
    @ApiResponses(value = {@ApiResponse(code = 204, message = "No Content")})
    @ApiOperation(value = "deleteRoute", notes = "Route Controller")
    public RouteDto deleteRoute(int id) {
        return routeService.deleteRoute(id);
    }


    @PutMapping("/route")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Accepted")})
    @ApiOperation(value = "updateRouteUser", notes = "Route Controller")
    public RouteDto updateRouteUser(int id, @RequestBody RouteDto route) {
        return routeService.updateRouteUser(id, route.getUserDto().getId());
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