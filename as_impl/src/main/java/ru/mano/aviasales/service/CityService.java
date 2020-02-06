package ru.mano.aviasales.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.mano.aviasales.dto.CityDto;
import ru.mano.aviasales.entity.CityEntity;
import ru.mano.aviasales.mapper.CityMapper;
import ru.mano.aviasales.repository.CityRepository;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Optional;

@Component
public class CityService {

    @Autowired
    private CityRepository repository;

    @Autowired
    private CityMapper cityMapper;

    public CityDto getCity(String id) {
        Optional<CityEntity> city = repository.findById(id);

        CityEntity cityEntity = city.orElseThrow(IllegalArgumentException::new);
        return cityMapper.from(cityEntity);
    }

    public CityDto createCity(String name, double x, double y) {
        CityDto city = new CityDto(name, x, y);
        repository.save(cityMapper.from(city));   //стоит ли проверять РК на уникальность?

        return city;
    }

    public CityDto updateCity(String cityId, CityDto city) {
        if(cityId != null && repository.existsById(cityId) ) {
            city.setId(cityId);
            repository.deleteById(cityId);
            repository.save(cityMapper.from(city));

            return city;
        } else
            throw new IllegalArgumentException("Can\'t update city with id " + cityId);
    }



    public CityDto deleteCity(String id) {
        if(id != null) {
            CityDto deleted = getCity(id);
            repository.deleteById(id);
            return deleted;
        } else
            return null;
    }
}
