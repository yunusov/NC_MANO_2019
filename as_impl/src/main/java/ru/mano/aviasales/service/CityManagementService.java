package ru.mano.aviasales.service;

import ru.mano.aviasales.model.City;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class CityManagementService {
    private static List<City> cityList = new LinkedList<>();
    private static long nextId = 0;
    private static CityManagementService instance;

    static {
        instance = new CityManagementService();
    }

    private CityManagementService() {
    }

    public static CityManagementService getInstance() {
        return instance;
    }

    public long createCity(double x, double y, String name) {
        long id = generateNewId();
        cityList.add(new City(id, x, y, name));
        return id;
    }

    public City getCityById(long id) {
        return cityList.stream()
                .filter(t -> t.getId() == id)
                .findAny()
                .get();
    }

    public List<City> getCitiesByName(String name) {
        return cityList.stream()
                .filter(t -> t.getName().equals(name))
                .collect(Collectors.toList());
    }

    public void updateCoordinates(long id, double newX, double newY) {
        City toUpdate = getCityById(id);
        toUpdate.setX(newX);
        toUpdate.setY(newY);
    }

    public void updateName(long id, String newName) {
        City toUpdate = getCityById(id);
        toUpdate.setName(newName);
    }

    public void delete(long id) {
        cityList.removeIf(city -> city.getId() == id);
    }

    private long generateNewId() {
        return nextId++;
    }
}
