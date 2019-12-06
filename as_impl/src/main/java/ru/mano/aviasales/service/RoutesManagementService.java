package ru.mano.aviasales.service;

import ru.mano.aviasales.model.Route;
import ru.mano.aviasales.model.Ticket;
import ru.mano.aviasales.model.User;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class RoutesManagementService {

    private static List<Route> routesStorage = new LinkedList<>();
    private static long nextId;

    public void createRoute(List<Ticket> list, User owner){
        routesStorage.add(new Route(generateNewId(), list, owner));
    }

    public Route getRoute(long id){
        return routesStorage.stream()
                .filter(route -> route.getId() == id)
                .collect(Collectors.toList());  //wtf
    }

    public List<Route> getUsersRoutes(User owner){
        List<Route> result = new LinkedList<>();
        for (Route r : routesStorage){
            if (r.getOwner().getId() == owner.getId()){
                result.add(new Route(r));
            }
        }
        return result;
    }

    public void updateRoute(){

    }

    public void deleteRoute(){

    }

    private long generateNewId() {
        return nextId++;
    }
}
