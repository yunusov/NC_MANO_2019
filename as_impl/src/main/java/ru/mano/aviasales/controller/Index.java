package ru.mano.aviasales.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.*;
import ru.mano.aviasales.entity.Ticket;
import ru.mano.aviasales.service.TicketService;

@RestController
public class Index {
    TicketService ticketService = TicketService.getInstance();


    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @PostMapping("/distance")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK")})
    @ApiOperation(value = "getDistance", notes = "Demo Controller")
    public double getDistance(@RequestBody Ticket ticket) {
        return ticketService.distance(ticket);
    }
}
