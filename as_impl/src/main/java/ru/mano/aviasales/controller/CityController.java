package ru.mano.aviasales.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.mano.aviasales.dto.CityDto;
import ru.mano.aviasales.service.CityManagementService;

import java.util.List;

@RestController
@RequestMapping("/city")
public class CityController {
    @Autowired
    private CityManagementService cityService;

    @PostMapping("/createCity")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "OK")})
    @ApiOperation(value = "create", notes = "CityController")
    public CityDto createCity(double x, double y, String name) {
        return cityService.createCity(x, y, name);
    }

    @GetMapping("/{id}")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK")})
    @ApiOperation(value = "get", notes = "CityController")
    public CityDto getCity(@RequestParam long id) {
        try {
            return cityService.getCityById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/getWithSameName")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK")})
    @ApiOperation(value = "getList", notes = "CityController")
    public List<CityDto> getCitiesByName(String name) {
        return cityService.getCitiesByName(name);
    }

    @PutMapping("/{id}")
    @ApiResponses(value = {@ApiResponse(code = 204, message = "NO CONTENT")})
    @ApiOperation(value = "updateCoordinates", notes = "CityController")
    public CityDto updateCoordinates(long id, double newX, double newY) {
        CityDto update = null;
        try {
            update = cityService.updateCoordinates(id, newX, newY);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return update;
    }

    @PutMapping("/updateName")
    @ApiResponses(value = {@ApiResponse(code = 204, message = "NO CONTENT")})
    @ApiOperation(value = "updateName", notes = "CityController")
    public CityDto updateName(long id, String newName) {
        CityDto update = null;
        try {
            update = cityService.updateName(id, newName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return update;
    }

    @DeleteMapping
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK")})
    @ApiOperation(value = "delete", notes = "CityController")
    public void delete(long id) {
        cityService.delete(id);
    }
}
