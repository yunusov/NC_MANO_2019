package ru.mano.aviasales.service;

import ru.mano.aviasales.dto.Role;
import ru.mano.aviasales.dto.UserDto;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class UserManagementService {

    private static List<UserDto> storage = new LinkedList<>();
    private static long nextId = 0;
    private static UserManagementService instance;

    static {
        instance = new UserManagementService();
    }

    private UserManagementService() {
    }

    public static UserManagementService getInstance() {
        return instance;
    }


    public long createUser(String name) {
        long id = generateNewId();
        storage.add(new UserDto(id, name, Role.USER));
        return id;
    }

    public UserDto getUser(long id) {
        return storage.stream()
                .filter(e -> e.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void updateUsersName(long id, String name) {  //пока что метод на апдейт имени пользователя, т.к. других полей нет
        UserDto user;
        try {
            user = getUser(id);
            user.setName(name);
        } catch (NoSuchElementException e) {
            System.out.println("There is no user with id " + id);
        }
    }

    public void deleteUser(long id) {
        Optional<UserDto> user = Optional.ofNullable(getUser(id));
        if (user.isPresent()) {
            storage.remove(storage.indexOf(getUser(id)));
        } else {
            System.out.println("Can not complete deletion, because user with id " + id + " does not exists");
        }
    }

    private long generateNewId() {
        return nextId++;
    }
}
