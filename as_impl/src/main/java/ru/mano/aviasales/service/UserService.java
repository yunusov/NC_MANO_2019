package ru.mano.aviasales.service;


import ru.mano.aviasales.entity.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Optional;

public class UserService {
    private static UserService userService;
    private ArrayList<User> storage = new ArrayList<>();
    private static int nextId = 0;

    static {
        userService = new UserService();
    }

    public static UserService getInstance() {
        return userService;
    }

    private UserService() {
    }


    public User getUser(int id) {
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

    public User createUser(String name) {
        User user = new User(generateNewId(), name, User.Role.USER);
        if (storage.add(user) )
            return user;
        System.out.println( "Can\'t add new User (" + user + ") in storage ");
        return null;

    }

    public User updateUsersName(int id, String newName) {
        User user = getUser(id);
        if (user != null) {
            user.setName(newName);
            return user;
        }
        System.out.println("There is no user with id " + id);
        return null;
    }

    public User deleteUser(int id) {
        User user = getUser(id);
        if (user == null) {
            System.out.println("Can\'t complete deletion, because user with id " + id + " does not exists");
            return null;
        } else if(!storage.remove(user)) {
            System.out.println("Can\'t complete deletion of existing user ");
            return null;
        }
        return user;
    }

    private int generateNewId() {
        return nextId++;
    }
}
