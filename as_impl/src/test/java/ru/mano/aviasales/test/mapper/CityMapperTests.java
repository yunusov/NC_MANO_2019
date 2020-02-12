package ru.mano.aviasales.test.mapper;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import ru.mano.aviasales.dto.CityDto;
import ru.mano.aviasales.entity.City;
import ru.mano.aviasales.mapper.CityMapper;

@RunWith(JUnit4.class)
public class CityMapperTests {

    @Test
    public void mapEntityToDtoTest() throws Exception {
        City entity = new City(1, 2, 3, "name");
        CityDto dto = CityMapper.mapTo(entity);
        Assert.assertTrue(entity.getId() == dto.getId()
                && entity.getName().equals(dto.getName())
                && (Double.compare(entity.getX(), dto.getX()) == 0)
                && (Double.compare(entity.getY(), dto.getY()) == 0));
    }

    @Test
    public void mapDtoToEntityTest() throws Exception {
        CityDto dto = new CityDto(1, 2, 3, "name");
        City entity = CityMapper.mapTo(dto);
        Assert.assertTrue(entity.getId() == dto.getId()
                && entity.getName().equals(dto.getName())
                && (Double.compare(entity.getX(), dto.getX()) == 0)
                && (Double.compare(entity.getY(), dto.getY()) == 0));
    }
}
