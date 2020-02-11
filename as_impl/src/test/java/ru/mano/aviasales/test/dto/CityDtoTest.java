package ru.mano.aviasales.test.dto;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import ru.mano.aviasales.dto.CityDto;

@RunWith(JUnit4.class)
public class CityDtoTest {

    @Test
    public void createTest() throws Exception {
        CityDto dto = new CityDto(0, 1, 2, "new_city");
        Assert.assertNotNull(dto);
        //Assert.assertEquals();
    }

    @Test
    public void gettersTest() throws Exception {
        CityDto dto = new CityDto(0, 1, 2, "new_city");
        Assert.assertEquals(0, dto.getId());
        //Assert.assertEquals(1, dto.getX());
        //Assert.assertEquals(2, dto.getY());
        Assert.assertEquals("new_city", dto.getName());
    }
}
