package ru.mano.aviasales.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ru.mano.aviasales.entity.User;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("demo")
public class Controller {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("info")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK")})
    @ApiOperation(value = "getInfo", notes = "Demo Controller")
    public String getInfo() {
        return "hello";
    }

    @PostMapping("user")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "OK")})
    @ApiOperation(value = "createUser", notes = "Demo Controller")
    public User createUser(@RequestBody User user) {
        return user;
    }

    @PostMapping("resendTest")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "OK")})
    @ApiOperation(value = "resendTest", notes = "Demo Controller")
    public String resendTest(String data) {
        Map<String, String> map = new HashMap<>();
        ResponseEntity<String> forEntity = restTemplate.getForEntity("http://localhost:8080/demo/info", String.class, map);
        System.out.println("getStatusCode: " + forEntity.getStatusCode());
        System.out.println("body: " + forEntity.getBody());
        return forEntity.getBody();
    }
}
