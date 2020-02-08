package ru.mano.aviasales.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.mano.aviasales.dto.UserDto;
import ru.mano.aviasales.service.UserManagementService;

@RestController
@RequestMapping("users")
public class UserController {
    @Autowired
    private UserManagementService userService;

    @PostMapping("add")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "OK")})
    @ApiOperation(value = "addUser", notes = "UserController")
    public UserDto addUser(@RequestBody String name) {
        return userService.createUser(name);
    }

    @PutMapping("update")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK")})
    @ApiOperation(value = "updateUsername", notes = "UserController")
    public UserDto updateUsername(long id, String newName) {
        UserDto user = null;
        try {
            user = userService.updateUsersName(id, newName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    @GetMapping("get")
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
