package ru.mano.aviasales;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.mano.aviasales.dto.CityDto;
import ru.mano.aviasales.repository.CityRepository;
import ru.mano.aviasales.service.CityService;

@SpringBootTest
//@RunWith(SpringRunner.class)
public class CityTest {
    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private CityService cityService;


    @Test
    public void createCity(){
        CityDto city = cityService.createCity("Moscow", 0, 0);
        Assert.assertEquals(city, cityService.getCity(city.getId()));
    }


    @Test
    public void updateCty(){
        CityDto oldCity = cityService.createCity("Moscow", 0, 0);
        CityDto newCity = new CityDto("Tolyatti", 100, -200);

        cityService.updateCity(oldCity.getId(), newCity);
        Assert.assertEquals(newCity, cityService.getCity((newCity.getId())));
    }

    @Test
    public void deleteCity() {
        CityDto city = cityService.createCity("Oktyabrsk", 150, -300);
        cityService.deleteCity(city.getId());
        if(cityRepository.existsById(city.getId()) == true )
            throw new RuntimeException("Unable to delete user from BD");
    }

}
