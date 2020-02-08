package ru.mano.aviasales.mapper;

import ru.mano.aviasales.dto.CityDto;
import ru.mano.aviasales.entity.City;

public class CityMapper {

    public static City mapTo(CityDto dto) {
        return new City(dto.getId(), dto.getX(), dto.getY(), dto.getName());
    }

    public static CityDto mapTo(City entity) {
        return new CityDto(entity.getId(), entity.getX(), entity.getY(), entity.getName());
    }
}
