package ru.mano.aviasales.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.mano.aviasales.dto.CityDto;
import ru.mano.aviasales.entity.City;
import ru.mano.aviasales.mapper.CityMapper;
import ru.mano.aviasales.repository.CityRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class CityManagementService {

    @Autowired
    private CityRepository cityRepository;
    private static long nextId = 0;

    public CityDto createCity(double x, double y, String name) {
        long id = generateNewId();
        City city = cityRepository.save(new City(id, x, y, name));
        return CityMapper.mapTo(city);
    }

    public CityDto getCityById(long id) throws Exception {
        Optional<City> city = cityRepository.findById(id);
        if (city.isPresent()) {
            return CityMapper.mapTo(city.get());
        } else {
            throw new Exception("City with id " + id + " not found");
        }
    }

    public List<CityDto> getCitiesByName(String name) {
        return cityRepository.findAll().stream(). //TODO: create new method findByName in cityRepository and replace findAll() with it
                filter(city -> city.getName().equals(name)).
                map(CityMapper::mapTo).
                collect(Collectors.toList());
    }

    public CityDto updateCoordinates(long id, double newX, double newY) throws Exception {
        CityDto cityToUpdate = getCityById(id);
        cityToUpdate.setX(newX);
        cityToUpdate.setY(newY);
        cityRepository.save(CityMapper.mapTo(cityToUpdate));
        return cityToUpdate;
    }

    public CityDto updateName(long id, String newName) throws Exception {
        CityDto cityToUpdate = getCityById(id);
        cityToUpdate.setName(newName);
        cityRepository.save(CityMapper.mapTo(cityToUpdate));
        return cityToUpdate;
    }

    public void delete(long id) {
        cityRepository.deleteById(id);
    }

    private long generateNewId() {
        return nextId++;
    }
}
