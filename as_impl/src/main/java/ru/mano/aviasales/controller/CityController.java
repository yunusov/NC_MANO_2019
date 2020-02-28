package ru.mano.aviasales.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.mano.aviasales.dto.CityDto;
import ru.mano.aviasales.service.CityService;

@RestController
@RequestMapping("/city")
public class CityController {

    @Autowired
    private CityService cityService;

    @PostMapping("/")
    @ApiResponses(value = {@ApiResponse(code = 202, message = "Created")})
    @ApiOperation(value = "createCity", notes = "City Controller")
    public CityDto createCity(String name, double x, double y) {
        return cityService.createCity(name, x, y);
    }

    @GetMapping("/")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK")})
    @ApiOperation(value = "getCity", notes = "City Controller")
    public CityDto getCity(String id) {
            return cityService.getCity(id);
    }

    @PutMapping("/")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Accepted")})
    @ApiOperation(value = "updateCity", notes = "City Controller (returns old object)")
    public CityDto updateCity(String id, @RequestBody CityDto city) {
        return cityService.updateCity(id, city);
    }

    @DeleteMapping("/")
    @ApiResponses(value = {@ApiResponse(code = 204, message = "No Content")})
    @ApiOperation(value = "deleteCity", notes = "City Controller")
    public void deleteCity(String id) {
         cityService.deleteCity(id);
    }



}

