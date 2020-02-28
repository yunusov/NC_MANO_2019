package ru.mano.aviasales.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.mano.aviasales.dto.UserDto;
import ru.mano.aviasales.service.UserService;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/")
    @ApiResponses(value = {@ApiResponse(code = 202, message = "Created")})
    @ApiOperation(value = "createUser", notes = "User Controller")
    public UserDto createUser(String name) {
        return userService.createUser(name);
    }


    @GetMapping("/")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK")})
    @ApiOperation(value = "getUser", notes = "User Controller")
    public UserDto getUser(String id) {
            return userService.getUser(id); //Can return Null
    }


    @PutMapping("/")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Accepted")})
    @ApiOperation(value = "updateUserName", notes = "User Controller")
    public UserDto updateUserName(String id, UserDto newName) {
        return userService.updateUser(id, newName);
    }


    @DeleteMapping("/")
    @ApiResponses(value = {@ApiResponse(code = 204, message = "No Content")})
    @ApiOperation(value = "deleteUser", notes = "User Controller")
    public void deleteUser(String id) {
         userService.deleteUser(id);
    }




}
