package ru.mano.aviasales.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.mano.aviasales.dto.TicketDto;
import ru.mano.aviasales.service.TicketService;

@RestController
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @PostMapping("/ticket")
    @ApiResponses(value = {@ApiResponse(code = 202, message = "Created")})
    @ApiOperation(value = "createTicket", notes = "Ticket Controller")
    public TicketDto createTicket(String  fromId, String  toId, double cost)  {
        return ticketService.createTicket(fromId, toId, cost);
    }

    @GetMapping("/ticket")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK")})
    @ApiOperation(value = "getTicket", notes = "Ticket Controller")
    public TicketDto getTicket(String id) {
            return ticketService.getTicket(id);
    }

    @PutMapping("/ticket")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Accepted")})
    @ApiOperation(value = "updateTicket", notes = "Ticket Controller")
    public TicketDto updateTicket(String id, @RequestBody TicketDto ticketDto) {
        return ticketService.updateTicket(id, ticketDto);
    }

    @DeleteMapping("/ticket")
    @ApiResponses(value = {@ApiResponse(code = 204, message = "No Content")})
    @ApiOperation(value = "deleteTicket", notes = "Ticket Controller")
    public void deleteTicket(String id) {
          ticketService.deleteTicket(id);
    }


    @GetMapping("/ticket/distance")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK")})
    @ApiOperation(value = "getTicketDistanceById", notes = "Ticket Controller")
    public double getTicketDistanceById(String id) {
        return ticketService.getTicketDistanceById(id);
    }
}

/*
    @PutMapping("/ticket")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Accepted")})
    @ApiOperation(value = "updateTicketCost", notes = "Ticket Controller")
    public Ticket updateTicketCost(int id, double cost) {
        return ticketService.updateTicketCost(id, cost);
    }

    @PutMapping("/ticket")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Accepted")})
    @ApiOperation(value = "updateDepartureCity", notes = "Ticket Controller")
    public Ticket updateDepartureCity (int id, int fromId ) {
        return ticketService.updateTicketFrom(id, fromId);
    }

    @PutMapping("/ticket")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Accepted")})
    @ApiOperation(value = "updateArrivalCity", notes = "Ticket Controller")
    public Ticket updateArrivalCity (int id, int toId ) {
        return ticketService.updateTicketTo(id, toId);
    }
*/

    /*
    @PostMapping("/ticket")
    @ApiResponses(value = {@ApiResponse(code = 202, message = "Created")})
    @ApiOperation(value = "createTicket", notes = "Ticket Controller")
    public Ticket createTicket(City fromId, City toId, double cost)  {
        return ticketService.createTicket(fromId, toId, cost);
    }
*/