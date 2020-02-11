package ru.mano.aviasales.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.mano.aviasales.dto.Role;
import ru.mano.aviasales.dto.UserDto;
import ru.mano.aviasales.entity.User;
import ru.mano.aviasales.mapper.UserMapper;
import ru.mano.aviasales.repository.UserRepository;

import java.util.Optional;

@Component
public class UserManagementService {

    @Autowired
    private UserRepository userRepository;
    private static long nextId = 0;

    public UserDto createUser(String name) {
        long id = generateNewId();
        userRepository.save(new User(id, name, ru.mano.aviasales.entity.Role.USER));
        return new UserDto(id, name, Role.USER);
    }

    public UserDto getUser(long id) throws Exception {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return UserMapper.mapTo(user.get());
        } else {
            throw new Exception("User with id " + id + " not found");
        }
    }

    public UserDto updateUsersName(long id, String name) throws Exception {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            user.get().setName(name);
            userRepository.save(user.get());
            return UserMapper.mapTo(user.get());
        } else {
            return null;
        }
    }

    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }

    private long generateNewId() {
        return nextId++;
    }
}
