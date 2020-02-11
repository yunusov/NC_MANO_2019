package ru.mano.aviasales.test.dto;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import ru.mano.aviasales.dto.Role;
import ru.mano.aviasales.dto.UserDto;

@RunWith(JUnit4.class)
public class UserDtoTest {

    @Test
    public void createTest() throws Exception {
        UserDto user = new UserDto(0,null, Role.USER);
        Assert.assertNotNull(user);
    }

    @Test
    public void gettersTest() throws Exception {
        long id = 0;
        String name = "name";
        Role role = Role.USER;
        UserDto user = new UserDto(id, name, role);
        Assert.assertEquals(id, user.getId());
        Assert.assertEquals(name, user.getName());
        Assert.assertEquals(role, user.getRole());
    }

    @Test
    public void settersTest() throws Exception {
        UserDto user = new UserDto(11, "qwerty", Role.ADMIN);
        long id = 0;
        String name = "name";
        Role role = Role.USER;
        user.setId(id);
        user.setName(name);
        user.setRole(role);
        Assert.assertEquals(id, user.getId());
        Assert.assertEquals(name, user.getName());
        Assert.assertEquals(role, user.getRole());
    }

    @Test
    public void toStringTest() throws Exception {
        UserDto user = new UserDto(101, "Adam", Role.USER);
        String expected = "User{id=" + user.getId() + ", name='" + user.getName() + "', role=" + user.getRole() + "}";
        Assert.assertEquals(expected, user.toString());
    }
}
