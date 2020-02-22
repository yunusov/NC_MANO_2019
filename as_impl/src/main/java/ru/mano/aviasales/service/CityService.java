package ru.mano.aviasales.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
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

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public CityDto getCity(String id) {
        Optional<CityEntity> city = repository.findById(id);

        CityEntity cityEntity = city.orElseThrow(IllegalArgumentException::new);
        return cityMapper.from(cityEntity);
    }


    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public CityDto createCity(String name, double x, double y) {
        CityDto city = new CityDto(name, x, y);
        CityEntity cityEntity = repository.save(cityMapper.from(city));//TODO:

        return cityMapper.from(cityEntity);
    }


    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public CityDto updateCity(String cityId, CityDto city) {
        if(cityId != null && repository.existsById(cityId) ) {
            city.setId(cityId);
            CityEntity cityEntity = repository.save(cityMapper.from(city));

            return cityMapper.from(cityEntity); //TODO:
        } else {
            throw new IllegalArgumentException("Can\'t update city with id " + cityId);
        }
    }


    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void deleteCity(String id) {
        if(id != null) {
            repository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Can\'t delete city"); //TODO:
        }

    }
}
