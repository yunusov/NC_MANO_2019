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
import ru.mano.aviasales.service.UserService;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/")
public class UserController {

    @Autowired
    private RestTemplate restTemplate;
    private UserService userService = UserService.getInstance();

    @PostMapping("/user")
    @ApiResponses(value = {@ApiResponse(code = 202, message = "Created")})
    @ApiOperation(value = "createUser", notes = "User Controller")
    public User createUser(String name) {
        return userService.createUser(name);
    }

    @GetMapping("/user")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK")})
    @ApiOperation(value = "getUser", notes = "User Controller")
    public User getUser(int id) {
            return userService.getUser(id); //Can return Null
    }

    @PutMapping("/user")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Accepted")})
    @ApiOperation(value = "deleteUser", notes = "User Controller")
    public User updateUserName(int id, String newName) {
        return userService.updateUsersName(id, newName);
    }

    @DeleteMapping("/user")
    @ApiResponses(value = {@ApiResponse(code = 204, message = "No Content")})
    @ApiOperation(value = "updateUser", notes = "User Controller")
    public User deleteUser(int id) {
        return userService.deleteUser(id);
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
    }
    */

}
