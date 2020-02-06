package ru.mano.aviasales.mapper;


import org.springframework.stereotype.Component;
import ru.mano.aviasales.dto.CityDto;
import ru.mano.aviasales.entity.CityEntity;

@Component
public class CityMapper {
    public CityDto from(CityEntity cityEntity) {
        return new CityDto(
                cityEntity.getId(),
                cityEntity.getName(),
                cityEntity.getX(),
                cityEntity.getY()
        );
    }

    public CityEntity from(CityDto cityDto) {
        return new CityEntity(
                cityDto.getId(),
                cityDto.getName(),
                cityDto.getX(),
                cityDto.getY()
        );
    }
}
