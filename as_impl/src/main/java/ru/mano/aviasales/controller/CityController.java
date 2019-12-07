package ru.mano.aviasales.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.*;
import ru.mano.aviasales.model.City;
import ru.mano.aviasales.service.CityManagementService;

import java.util.List;

@RestController
@RequestMapping("city")
public class CityController {
    private CityManagementService cityService = CityManagementService.getInstance();

    @PostMapping("createCity")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "OK")})
    @ApiOperation(value = "create", notes = "CityController")
    public long createCity(double x, double y, String name){
        return cityService.createCity(x, y, name);
    }

    @GetMapping("get")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK")})
    @ApiOperation(value = "get", notes = "CityController")
    public City getCity(long id){
        return cityService.getCityById(id);
    }

    @GetMapping("getWithSameName")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK")})
    @ApiOperation(value = "getList", notes = "CityController")
    public List<City> getCitiesByName(String name){
        return cityService.getCitiesByName(name);
    }

    @PutMapping("updateCoord")
    @ApiResponses(value = {@ApiResponse(code = 204, message = "NO CONTENT")})
    @ApiOperation(value = "updateCoordinates", notes = "CityController")
    public void updateCoordinates(long id, double newX, double newY){
        cityService.updateCoordinates(id, newX, newY);
    }

    @PutMapping("updateName")
    @ApiResponses(value = {@ApiResponse(code = 204, message = "NO CONTENT")})
    @ApiOperation(value = "updateName", notes = "CityController")
    public void updateName(long id, String newName){
        cityService.updateName(id, newName);
    }

    @DeleteMapping("delete")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK")})
    @ApiOperation(value = "delete", notes = "CityController")
    public void delete(long id){
        cityService.delete(id);
    }
}
