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
        UserEntity userEntity = repository.save(userMapper.from(userDto));

        return userMapper.from(userEntity);
    }

    public UserDto createAdmin(String name) {
        UserDto userDto = new UserDto(name, Role.ADMIN);
        UserEntity userEntity = repository.save(userMapper.from(userDto));

        return userMapper.from(userEntity);
    }

    public UserDto updateUser(String userId, UserDto newUser) {
        if(userId != null && repository.existsById(userId) ) {
            newUser.setId(userId);
            UserEntity userEntity = repository.save(userMapper.from(newUser));

            return userMapper.from((userEntity));
        } else {
            throw new IllegalArgumentException("Can\'t update user with id " + userId);
        }
    }

    public void deleteUser(String userId) {
        if(userId != null) {
            repository.deleteById(userId);
        } else {
            throw new IllegalArgumentException("Can\'t delete user " );
        }
    }

}
