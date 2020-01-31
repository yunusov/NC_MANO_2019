package ru.mano.aviasales.service;


import ru.mano.aviasales.dto.UserDto;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class UserService {
    private static UserService userService;
    private ArrayList<UserDto> storage = new ArrayList<>();
    private static int nextId = 0;

    static {
        userService = new UserService();
    }

    public static UserService getInstance() {
        return userService;
    }

    private UserService() {
    }


    public UserDto getUser(int id) {
        try {
            return storage.stream()
                    .filter(u -> u.getId() == id)
                    .findAny()
                    .orElseThrow(NoSuchElementException::new);
        } catch (NoSuchElementException e) {
            System.out.println( "Can\'t get User with id: " + id + '\n' + e.getMessage());
            return null;
        }
    }

    public UserDto createUser(String name) {
        UserDto userDto = new UserDto(generateNewId(), name, UserDto.Role.USER);
        if (storage.add(userDto) )
            return userDto;
        System.out.println( "Can\'t add new User (" + userDto + ") in storage ");
        return null;

    }

    public UserDto updateUsersName(int id, String newName) {
        UserDto userDto = getUser(id);
        if (userDto != null) {
            userDto.setName(newName);
            return userDto;
        }
        System.out.println("There is no user with id " + id);
        return null;
    }

    public UserDto deleteUser(int id) {
        UserDto userDto = getUser(id);
        if (userDto == null) {
            System.out.println("Can\'t complete deletion, because user with id " + id + " does not exists");
            return null;
        } else if(!storage.remove(userDto)) {
            System.out.println("Can\'t complete deletion of existing user ");
            return null;
        }
        return userDto;
    }

    private int generateNewId() {
        return nextId++;
    }
}
