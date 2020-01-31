package ru.mano.aviasales.service;


import ru.mano.aviasales.dto.CityDto;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class CityService {
    private static CityService cityService;
    private ArrayList<CityDto> storage = new ArrayList<>();
    private static int nextId = 0;

    static {
        cityService = new CityService();
    }

    public static CityService getInstance() {
        return cityService;
    }

    private CityService() {
    }


    public CityDto getCity(int id) {
        try {
            return storage.stream()
                    .filter(c -> c.getId() == id)
                    .findAny()
                    .orElseThrow(NoSuchElementException::new);
        } catch (NoSuchElementException e) {
            System.out.println( "Can\'t get City with id: " + id + '\n' + e.getMessage() );
            return null;
        }
    }

    public CityDto createCity(String name, double x, double y) {
        CityDto city = new CityDto(generateNewId(), name, x, y);
        if (storage.add(city) )
            return city;
        else
            System.out.println( "Can\'t add new City (" + city + ") in storage ");
        return null;

    }

    public CityDto updateCity(int cityId, CityDto city) {
        //TODO: some check
        return storage.set(cityId, city);
    }

    @Deprecated
    public CityDto updateCityName(int id, String newName) {
        CityDto city = getCity(id);
        if (city != null) {
            city.setName(newName);
            return city;
        }
        System.out.println("There is no city with id " + id);
        return null;
    }

    @Deprecated
    public CityDto updateCityCoordinate(int id, double x, double y) {
        CityDto city = getCity(id);
        if (city != null) {
            city.setX(x);
            city.setY(y);
            return city;
        }
        System.out.println("There is no city with id " + id);
        return null;
    }


    public CityDto deleteCity(int id) {
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

    private int generateNewId() {
        return nextId++;
    }
}
