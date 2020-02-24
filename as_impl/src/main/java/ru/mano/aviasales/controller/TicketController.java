package ru.mano.aviasales.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.mano.aviasales.dto.CityDto;
import ru.mano.aviasales.dto.TicketDto;
import ru.mano.aviasales.service.TicketsManagementService;

import java.util.List;

@RestController
@RequestMapping("tickets")
public class TicketController {

    @Autowired
    private TicketsManagementService ticketService;

    @PostMapping("createTicket")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "OK")})
    @ApiOperation(value = "createTicket", notes = "TicketController")
    public TicketDto createTicket(@RequestParam Long source, @RequestParam Long destination) {
        return ticketService.createTicket(source, destination);
    }

    @PutMapping("updateTicket/{id}")
    @ApiResponses(value = {@ApiResponse(code = 204, message = "NO CONTENT")})
    @ApiOperation(value = "updateTicket", notes = "TicketController")
    public TicketDto updateTicket(@PathVariable long id, CityDto newSource, CityDto newDestination) {
        TicketDto ticketDto = null;
        try {
            ticketDto = ticketService.updateTicket(id, newSource, newDestination);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ticketDto;
    }

    @PutMapping("updateSameTickets")
    @ApiResponses(value = {@ApiResponse(code = 204, message = "NO CONTENT")})
    @ApiOperation(value = "updateTickets", notes = "TicketController")
    public List<TicketDto> updateTickets(CityDto source, CityDto destination, CityDto newSource, CityDto newDestination) {
        return ticketService.updateTickets(source, destination, newSource, newDestination);
    }

    @PutMapping("updateTickets")
    @ApiResponses(value = {@ApiResponse(code = 204, message = "NO CONTENT")})
    @ApiOperation(value = "createRoute", notes = "TicketController")
    public List<TicketDto> updateTickets(List<Long> ids, CityDto newSource, CityDto newDestination) {
        return ticketService.updateTickets(ids, newSource, newDestination);
    }

    @GetMapping("getTickets")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK")})
    @ApiOperation(value = "getTickets", notes = "TicketController")
    public List<TicketDto> getTickets(CityDto source, CityDto destination) {
        return ticketService.getTickets(source, destination);
    }

    @GetMapping("getTicket")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK")})
    @ApiOperation(value = "getTicketById", notes = "TicketController")
    public TicketDto getTicketById(long id) {
        TicketDto ticket = null;
        try {
            ticket = ticketService.getTicketById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ticket;
    }

    @DeleteMapping("deleteSameTickets")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK")})
    @ApiOperation(value = "deleteTickets", notes = "TicketController")
    public void deleteTickets(CityDto source, CityDto destination) {
        ticketService.deleteTickets(source, destination);
    }

    @DeleteMapping("deleteTicket")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK")})
    @ApiOperation(value = "deleteTicketsById", notes = "TicketController")
    public void deleteTicketsById(long id) {
        ticketService.deleteTicketById(id);
    }
}
