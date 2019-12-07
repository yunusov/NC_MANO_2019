package ru.mano.aviasales.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.*;
import ru.mano.aviasales.model.City;
import ru.mano.aviasales.model.Ticket;
import ru.mano.aviasales.service.TicketsManagementService;

import java.util.List;

@RestController
@RequestMapping("tickets")
public class TicketController {
    private TicketsManagementService ticketService = TicketsManagementService.getInstance();

    @PostMapping("createTicket")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "OK")})
    @ApiOperation(value = "createTicket", notes = "TicketController")
    public long createTicket(City source, City destination) {
        return ticketService.createTicket(source, destination);
    }

    @PutMapping("updateTicket")
    @ApiResponses(value = {@ApiResponse(code = 204, message = "NO CONTENT")})
    @ApiOperation(value = "updateTicket", notes = "TicketController")
    public void updateTicket(long id, City newSource, City newDestination) {
        ticketService.updateTicket(id, newSource, newDestination);
    }

    @PutMapping("updateSameTickets")
    @ApiResponses(value = {@ApiResponse(code = 204, message = "NO CONTENT")})
    @ApiOperation(value = "updateTickets", notes = "TicketController")
    public void updateTickets(City source, City destination, City newSource, City newDestination) {
        ticketService.updateTickets(source, destination, newSource, newDestination);
    }

    @PutMapping("updateTickets")
    @ApiResponses(value = {@ApiResponse(code = 204, message = "NO CONTENT")})
    @ApiOperation(value = "createRoute", notes = "TicketController")
    public void updateTickets(List<Long> ids, City newSource, City newDestination) {
        ticketService.updateTickets(ids, newSource, newDestination);
    }

    @GetMapping("getTickets")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK")})
    @ApiOperation(value = "getTickets", notes = "TicketController")
    public List<Ticket> getTickets(City source, City destination) {
        return ticketService.getTickets(source, destination);
    }

    @GetMapping("getTicket")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK")})
    @ApiOperation(value = "getTicketById", notes = "TicketController")
    public Ticket getTicketById(long id) {
        return ticketService.getTicketById(id);
    }

    @DeleteMapping("deleteSameTickets")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK")})
    @ApiOperation(value = "deleteTickets", notes = "TicketController")
    public void deleteTickets(City source, City destination) {
        ticketService.deleteTickets(source, destination);
    }

    @DeleteMapping("deleteTicket")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK")})
    @ApiOperation(value = "deleteTicketsById", notes = "TicketController")
    public void deleteTicketsById(long id) {
        ticketService.deleteTicketsById(id);
    }
}
