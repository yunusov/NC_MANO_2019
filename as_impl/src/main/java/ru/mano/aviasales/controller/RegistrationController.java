package ru.mano.aviasales.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.mano.aviasales.dto.UserDto;
import ru.mano.aviasales.exception.UserAlreadyExistsException;
import ru.mano.aviasales.service.UserManagementService;

@Controller //Почему не работает, если использовать @RestController?
@RequestMapping("/registration")
public class RegistrationController {

    @Autowired
    private UserManagementService userService;

    @GetMapping
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK")})
    @ApiOperation(value = "showRegistrationPage", notes = "RegistrationController")
    public String showRegistration() {
        return "registration";
    }

    @PostMapping
    @ApiResponses(value = {@ApiResponse(code = 201, message = "OK")})
    @ApiOperation(value = "registerUser", notes = "RegisterController")
    public String registerNewUser(Model model, String name, String username, String password) {
        UserDto user;
        try {
            user = userService.createUser(name, username, password);
        } catch (UserAlreadyExistsException e) {
            return "redirect:/registration?error";
        }
        return "redirect:/login";
    }
}
