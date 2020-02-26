package ru.mano.aviasales.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.mano.aviasales.dto.Role;
import ru.mano.aviasales.dto.UserDto;
import ru.mano.aviasales.entity.User;
import ru.mano.aviasales.mapper.UserMapper;
import ru.mano.aviasales.repository.UserRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Component
public class UserManagementService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    private static long nextId = 0;

    //TODO: если вызывается при регистрации пользователя, то добавить password и username
    public UserDto createUser(String name, String username, String password) {
        //long id = generateNewId();
        User entity = new User();
        Set<ru.mano.aviasales.entity.Role> roles = new HashSet<>();
        roles.add(ru.mano.aviasales.entity.Role.USER);
        entity.setName(name);
        entity.setRole(roles);
        entity.setEnabled(true);
        entity.setUsername(username);
        entity.setPassword(passwordEncoder.encode(password));
        entity = userRepository.save(entity);
        return new UserDto(entity.getId(), name, Role.USER);
    }

    public UserDto getUser(long id) throws Exception {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return UserMapper.mapTo(user.get());
        } else {
            throw new Exception("User with id " + id + " not found");
        }
    }

    public UserDto updateUsersName(long id, String name) {
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

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }
}