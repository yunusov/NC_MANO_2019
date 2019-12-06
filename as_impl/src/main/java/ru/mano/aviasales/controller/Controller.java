package ru.mano.aviasales.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ru.mano.aviasales.entity.User;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/")
public class Controller {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("info")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK")})
    @ApiOperation(value = "getInfo", notes = "Demo Controller")
    public String getInfo() {
        return "Hey there";
    }



    @GetMapping("user")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK")})
    @ApiOperation(value = "getUser", notes = "Demo Controller")
    public String getUser(int id) {
        return "Here is your User...";
    }

    @PostMapping("user")
    @ApiResponses(value = {@ApiResponse(code = 202, message = "Created")})
    @ApiOperation(value = "createUser", notes = "Demo Controller")
    public User createUser(@RequestBody User user) {
        return user;
    }

    @PutMapping("user")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Accepted")})
    @ApiOperation(value = "deleteUser", notes = "Demo Controller")
    public String deleteUser(User user) {
        return "Here is your User...";
    }

    @DeleteMapping("user")
    @ApiResponses(value = {@ApiResponse(code = 204, message = "No Content")})
    @ApiOperation(value = "updateUser", notes = "Demo Controller")
    public String updateUser(User user) {
        return "Here is your User...";
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
