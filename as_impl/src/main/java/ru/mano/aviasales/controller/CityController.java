package ru.mano.aviasales.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ru.mano.aviasales.entity.City;
import ru.mano.aviasales.service.CityService;
import ru.mano.aviasales.service.UserService;

@RestController
@RequestMapping("/")
public class CityController {

    @Autowired
    //private RestTemplate restTemplate;
    private CityService cityService = CityService.getInstance();

    @PostMapping("/city")
    @ApiResponses(value = {@ApiResponse(code = 202, message = "Created")})
    @ApiOperation(value = "createCity", notes = "City Controller")
    public City createCity(String name, double x, double y) {
        return cityService.createCity(name, x, y);
    }

    @GetMapping("/city")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK")})
    @ApiOperation(value = "getCity", notes = "City Controller")
    public City getCity(int id) {
            return cityService.getCity(id);
    }

    @PutMapping("/city")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Accepted")})
    @ApiOperation(value = "updateCityName", notes = "City Controller")
    public City updateCityName(int id, String newName) {
        return cityService.updateCityName(id, newName);
    }

    @PutMapping("/city")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Accepted")})
    @ApiOperation(value = "updateCityCoordinate", notes = "City Controller")
    public City updateCityCoordinate(int id, double x, double y) {
        return cityService.updateCityCoordinate(id, x, y);
    }

    @DeleteMapping("/city")
    @ApiResponses(value = {@ApiResponse(code = 204, message = "No Content")})
    @ApiOperation(value = "deleteCity", notes = "City Controller")
    public City deleteCity(int id) {
        return cityService.deleteCity(id);
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