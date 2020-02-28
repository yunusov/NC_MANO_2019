package ru.mano.aviasales.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.mano.aviasales.dto.UserDto;
import ru.mano.aviasales.service.UserManagementService;

import java.security.Principal;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserManagementService userService;

    @PutMapping("/update")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK")})
    @ApiOperation(value = "updateUsername", notes = "UserController")
    public UserDto updateUsername(long id, String newName) {
        UserDto user = null;
        user = userService.updateUsersName(id, newName);
        return user;
    }

    @GetMapping("/get")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK")})
    @ApiOperation(value = "getUser", notes = "UserController")
    public UserDto getUser(long id) {
        UserDto user = null;
        try {
            user = userService.getUser(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    @DeleteMapping
    @ApiResponses(value = {@ApiResponse(code = 204, message = "NO CONTENT")})
    public void deleteUser(@RequestBody long id) {
        userService.deleteUser(id);
    }
}
