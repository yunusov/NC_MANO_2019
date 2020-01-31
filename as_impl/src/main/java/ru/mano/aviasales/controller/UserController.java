package ru.mano.aviasales.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.*;
import ru.mano.aviasales.dto.UserDto;
import ru.mano.aviasales.service.UserService;


@RestController
//@RequestMapping("/")
public class UserController {

    private UserService userService = UserService.getInstance();

    @PostMapping("/user")
    @ApiResponses(value = {@ApiResponse(code = 202, message = "Created")})
    @ApiOperation(value = "createUser", notes = "User Controller")
    public UserDto createUser(String name) {
        return userService.createUser(name);
    }

    @GetMapping("/user")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK")})
    @ApiOperation(value = "getUser", notes = "User Controller")
    public UserDto getUser(int id) {
            return userService.getUser(id); //Can return Null
    }

    @PutMapping("/user")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Accepted")})
    @ApiOperation(value = "updateUserName", notes = "User Controller")
    public UserDto updateUserName(int id, String newName) {
        return userService.updateUsersName(id, newName);
    }

    @DeleteMapping("/user")
    @ApiResponses(value = {@ApiResponse(code = 204, message = "No Content")})
    @ApiOperation(value = "deleteUser", notes = "User Controller")
    public UserDto deleteUser(int id) {
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
