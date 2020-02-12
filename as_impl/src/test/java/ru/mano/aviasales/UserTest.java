package ru.mano.aviasales;


import org.h2.engine.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.mano.aviasales.dto.Role;
import ru.mano.aviasales.dto.UserDto;
import ru.mano.aviasales.entity.UserEntity;
import ru.mano.aviasales.mapper.UserMapper;
import ru.mano.aviasales.repository.UserRepository;
import ru.mano.aviasales.service.UserService;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;


    @Test
    public void createUser(){
        UserDto user = userService.createUser("Tom");
        Assert.assertEquals(user, userService.getUser(user.getId()));
    }


    @Test
    public void updateUser(){
        UserDto oldUser = userService.createAdmin("Tim");
        UserDto newUser = new UserDto("Anna", Role.ADMIN);

        userService.updateUser(oldUser.getId(), newUser);  // updateUser changes newUser.id
        Assert.assertEquals(newUser, userService.getUser((newUser.getId())));
    }

    @Test
    public void deleteUser() {
        UserDto user = userService.createUser("Aleksandr");
        userService.deleteUser(user.getId());
        if(userRepository.existsById(user.getId()) == true )
            throw new RuntimeException("Unable to delete user from BD");
    }

}
