package ru.mano.aviasales.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ru.mano.aviasales.dto.TicketDto;
import ru.mano.aviasales.service.TicketService;

//@RestController
public class Index {
    private TicketService ticketService = TicketService.getInstance();
    @Autowired
    private RestTemplate restTemplate;


    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @PostMapping("/distance")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK")})
    @ApiOperation(value = "getDistance", notes = "Demo Controller")
    public double getDistance(@RequestBody TicketDto ticketDto) {
        return ticketService.getDistance(ticketDto);
    }
/*
    @PostMapping("resendTest")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "OK")})
    @ApiOperation(value = "resendTest", notes = "Demo Controller")
    public String resendTest(String data) {
        Map<String, String> map = new HashMap<>();
        ResponseEntity<String> forEntity = restTemplate.getForEntity("http://localhost:8080/demo/info", String.class, map);
        System.out.println("getStatusCode: " + forEntity.getStatusCode());
        System.out.println("body: " + forEntity.getBody());
        return forEntity.getBody();
    }*/
}
