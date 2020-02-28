package ru.mano.aviasales.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.mano.aviasales.dto.TicketDto;
import ru.mano.aviasales.service.TicketService;



@RestController
@RequestMapping("/ticket")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @PostMapping("/")
    @ApiResponses(value = {@ApiResponse(code = 202, message = "Created")})
    @ApiOperation(value = "createTicket", notes = "Ticket Controller")
    public TicketDto createTicket(String  fromId, String  toId, double cost)  {
        return ticketService.createTicket(fromId, toId, cost);
    }

    @GetMapping("/")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK")})
    @ApiOperation(value = "getTicket", notes = "Ticket Controller")
    public TicketDto getTicket(String id) {
            return ticketService.getTicket(id);
    }

    @PutMapping("/")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Accepted")})
    @ApiOperation(value = "updateTicket", notes = "Ticket Controller")
    public TicketDto updateTicket(String id, @RequestBody TicketDto ticketDto) {
        return ticketService.updateTicket(id, ticketDto);
    }

    @DeleteMapping("/")
    @ApiResponses(value = {@ApiResponse(code = 204, message = "No Content")})
    @ApiOperation(value = "deleteTicket", notes = "Ticket Controller")
    public void deleteTicket(String id) {
          ticketService.deleteTicket(id);
    }


    @GetMapping("/distance")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK")})
    @ApiOperation(value = "getTicketDistanceById", notes = "Ticket Controller")
    public double getTicketDistanceById(String id) {
        return ticketService.getTicketDistanceById(id);
    }
}
