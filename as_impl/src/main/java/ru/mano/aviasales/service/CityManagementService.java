package ru.mano.aviasales.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.mano.aviasales.dto.CityDto;
import ru.mano.aviasales.entity.City;
import ru.mano.aviasales.repository.CityRepository;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CityManagementService {
    @Autowired
    private CityRepository repository;
    private static List<CityDto> cityList = new LinkedList<>();
    private static long nextId = 0;
    private static CityManagementService instance;



    private CityManagementService() {
    }

    public static CityManagementService getInstance() {
        return instance;
    }

    public long createCity(double x, double y, String name) {
        long id = generateNewId();
        cityList.add(new CityDto(id, x, y, name));
        City city = repository.save(new City(id, x, y, name));
        return city.getId();
    }

    public CityDto getCityById(long id) {
        return cityList.stream()
                .filter(t -> t.getId() == id)
                .findAny()
                .get();
    }

    public List<CityDto> getCitiesByName(String name) {
        return cityList.stream()
                .filter(t -> t.getName().equals(name))
                .collect(Collectors.toList());
    }

    public void updateCoordinates(long id, double newX, double newY) {
        CityDto toUpdate = getCityById(id);
        toUpdate.setX(newX);
        toUpdate.setY(newY);
    }

    public void updateName(long id, String newName) {
        CityDto toUpdate = getCityById(id);
        toUpdate.setName(newName);
    }

    public void delete(long id) {
        cityList.removeIf(city -> city.getId() == id);
    }

    private long generateNewId() {
        return nextId++;
    }
}
