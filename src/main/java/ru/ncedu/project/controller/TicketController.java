package ru.ncedu.project.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.*;
import ru.ncedu.project.dto.TicketDto;
import ru.ncedu.project.service.TicketService;

@RestController
public class TicketController {

    private TicketService ticketService = TicketService.getInstance();

    @PostMapping("/ticket")
    @ApiResponses(value = {@ApiResponse(code = 202, message = "Created")})
    @ApiOperation(value = "createTicket", notes = "Ticket Controller")
    public TicketDto createTicket(int fromId, int toId, double cost)  {
        return ticketService.createTicket(fromId, toId, cost);
    }

    @GetMapping("/ticket")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK")})
    @ApiOperation(value = "getTicket", notes = "Ticket Controller")
    public TicketDto getTicket(int id) {
            return ticketService.getTicket(id);
    }

    @PutMapping("/ticket")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Accepted")})
    @ApiOperation(value = "updateTicket", notes = "Ticket Controller")
    public TicketDto updateTicket(int id, @RequestBody TicketDto ticket) {
        return ticketService.updateTicket(id, ticket);
    }

    @DeleteMapping("/ticket")
    @ApiResponses(value = {@ApiResponse(code = 204, message = "No Content")})
    @ApiOperation(value = "deleteTicket", notes = "Ticket Controller")
    public TicketDto deleteTicket(int id) {
        return ticketService.deleteTicket(id);
    }


    @GetMapping("/ticket/distance")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK")})
    @ApiOperation(value = "getTicketDistanceById", notes = "Ticket Controller")
    public double getTicketDistanceById(int id) {
        return ticketService.getTicketDistanceById(id);
    }
}