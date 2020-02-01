package ru.mano.aviasales.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.*;
import ru.mano.aviasales.dto.UserDto;
import ru.mano.aviasales.service.UserManagementService;

@RestController
@RequestMapping("users")
public class UserController {
    private UserManagementService userService = UserManagementService.getInstance();

    @PostMapping("add")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "OK")})
    @ApiOperation(value = "addUser", notes = "UserController")
    public long addUser(@RequestBody String name) {
        return userService.createUser(name);
    }

    @PutMapping("update")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK")})
    @ApiOperation(value = "updateUsername", notes = "UserController")
    public UserDto updateUsername(@RequestBody long id, String newName) {
        userService.updateUsersName(id, newName);
        return userService.getUser(id);
    }

    @GetMapping("get")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK")})
    @ApiOperation(value = "getUser", notes = "UserController")
    public UserDto getUser(long id) {
        return userService.getUser(id);
    }

    @DeleteMapping
    @ApiResponses(value = {@ApiResponse(code = 204, message = "NO CONTENT")})
    public void deleteUser(@RequestBody long id) {
        userService.deleteUser(id);
    }
}
