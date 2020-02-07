package ru.mano.aviasales.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.mano.aviasales.dto.Role;
import ru.mano.aviasales.dto.UserDto;
import ru.mano.aviasales.entity.UserEntity;
import ru.mano.aviasales.mapper.UserMapper;
import ru.mano.aviasales.repository.UserRepository;
import java.util.Optional;


@Component
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private UserMapper userMapper;


    public UserDto getUser(String userId) {
        Optional<UserEntity> user = repository.findById(userId);

        UserEntity userEntity = user.orElseThrow(IllegalArgumentException::new);
        return userMapper.from(userEntity);
    }


    public UserDto createUser(String name) {
        UserDto userDto = new UserDto(name, Role.USER);
        repository.save(userMapper.from(userDto));   //стоит ли проверять РК на уникальность?

        return userDto;
    }

    public UserDto createAdmin(String name) {
        UserDto userDto = new UserDto(name, Role.ADMIN);
        repository.save(userMapper.from(userDto));   //стоит ли проверять РК на уникальность?

        return userDto;
    }

    public UserDto updateUser(String userId, UserDto newUser) {
        if(userId != null && repository.existsById(userId) ) {
            newUser.setId(userId);
            repository.deleteById(userId);
            repository.save(userMapper.from(newUser));

            return newUser;
        } else
            throw new IllegalArgumentException("Can\'t update user with id " + userId);
    }

    public UserDto deleteUser(String userId) {
        if(userId != null) {
            UserDto deleted = getUser(userId);
            repository.deleteById(userId);
            return deleted;
        } else
            return null;
    }

}
