package ru.mano.aviasales.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.mano.aviasales.entity.City;
import ru.mano.aviasales.entity.Ticket;
import ru.mano.aviasales.service.CityService;
import ru.mano.aviasales.service.TicketService;

@RestController
@RequestMapping("/")
public class TicketController {

    @Autowired
    //private RestTemplate restTemplate;
    private TicketService ticketService = TicketService.getInstance();

    @PostMapping("/ticket")
    @ApiResponses(value = {@ApiResponse(code = 202, message = "Created")})
    @ApiOperation(value = "createTicket", notes = "Ticket Controller")
    public Ticket createTicket(int fromId, int toId, double cost)  {
        return ticketService.createTicket(fromId, toId, cost);
    }
    /*
    @PostMapping("/ticket")
    @ApiResponses(value = {@ApiResponse(code = 202, message = "Created")})
    @ApiOperation(value = "createTicket", notes = "Ticket Controller")
    public Ticket createTicket(City fromId, City toId, double cost)  {
        return ticketService.createTicket(fromId, toId, cost);
    }
*/
    @GetMapping("/ticket")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK")})
    @ApiOperation(value = "getTicket", notes = "Ticket Controller")
    public Ticket getTicket(int id) {
            return ticketService.getTicket(id);
    }

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


    @DeleteMapping("/ticket")
    @ApiResponses(value = {@ApiResponse(code = 204, message = "No Content")})
    @ApiOperation(value = "deleteTicket", notes = "Ticket Controller")
    public Ticket deleteTicket(int id) {
        return ticketService.deleteTicket(id);
    }


    @GetMapping("/ticket")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK")})
    @ApiOperation(value = "getTicketDistanceById", notes = "Ticket Controller")
    public double getTicketDistanceById(int id) {
        return ticketService.getTicketDistanceById(id);
    }
}
