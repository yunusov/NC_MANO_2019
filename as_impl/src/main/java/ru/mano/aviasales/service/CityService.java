package ru.mano.aviasales.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.mano.aviasales.dto.CityDto;
import ru.mano.aviasales.entity.CityEntity;
import ru.mano.aviasales.repository.CityRepository;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Optional;

@Component
public class CityService {
   @Autowired
    private CityRepository repository;


    public CityDto getCity(String id) {
        Optional<CityEntity> city = repository.findById(id);
        CityEntity cityEntity = city.orElseThrow(IllegalArgumentException::new);

        //todo: convert to city dto
        return null; //return here dto
    }

    public CityDto createCity(String name, double x, double y) {
        CityDto city = new CityDto(generateNewId(), name, x, y);
        if (storage.add(city) )
            return city;
        else
            System.out.println( "Can\'t add new City (" + city + ") in storage ");
        return null;

    }

    public CityDto updateCity(String cityId, CityDto city) {
        //TODO: some check
        return storage.set(cityId, city);
    }

    @Deprecated
    public CityDto updateCityName(String id, String newName) {
        CityDto city = getCity(id);
        if (city != null) {
            city.setName(newName);
            return city;
        }
        System.out.println("There is no city with id " + id);
        return null;
    }

    @Deprecated
    public CityDto updateCityCoordinate(String id, double x, double y) {
        CityDto city = getCity(id);
        if (city != null) {
            city.setX(x);
            city.setY(y);
            return city;
        }
        System.out.println("There is no city with id " + id);
        return null;
    }


    public CityDto deleteCity(String id) {
        CityDto city = getCity(id);
        if (city == null) {
            System.out.println("Can\'t complete deletion, because city with id " + id + " does not exists");
            return null;
        } else if(!storage.remove(city)) {
            System.out.println("Can\'t complete deletion of existing city ");
            return null;
        }
        return city;
    }

}
