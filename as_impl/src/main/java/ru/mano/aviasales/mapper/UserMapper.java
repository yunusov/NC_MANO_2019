package ru.mano.aviasales.mapper;

import ru.mano.aviasales.dto.UserDto;
import ru.mano.aviasales.entity.Role;
import ru.mano.aviasales.entity.User;

public class UserMapper {

    public static User mapTo(UserDto dto) {
        return new User(dto.getId(),
                dto.getName(),
                Role.valueOf(dto.getRole().name()));
    }

    public static UserDto mapTo(User entity) {
        return new UserDto(entity.getId(),
                entity.getName(),
                ru.mano.aviasales.dto.Role.valueOf(entity.getRole().name()));
    }
}
