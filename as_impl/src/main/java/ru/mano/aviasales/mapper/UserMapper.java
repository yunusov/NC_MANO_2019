package ru.mano.aviasales.mapper;

import ru.mano.aviasales.dto.UserDto;
import ru.mano.aviasales.entity.User;

public class UserMapper {

    public static User mapTo(UserDto dto) {
        User entity = new User();
        entity.setName(dto.getName());
        entity.setId(dto.getId());
        entity.setRole(RoleMapper.mapTo(dto.getRole()));
        return entity;
    }

    public static UserDto mapTo(User entity) {
        UserDto dto = new UserDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setRole(RoleMapper.mapTo(entity.getRole()));
        return dto;
    }
}
