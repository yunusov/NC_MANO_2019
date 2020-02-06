package ru.mano.aviasales.mapper;


import org.springframework.stereotype.Component;
import ru.mano.aviasales.dto.CityDto;
import ru.mano.aviasales.dto.UserDto;
import ru.mano.aviasales.entity.CityEntity;
import ru.mano.aviasales.entity.UserEntity;

@Component
public class UserMapper {
    public UserDto from(UserEntity userEntity) {
        return new UserDto(
                userEntity.getId(),
                userEntity.getName(),
                userEntity.getRole()
        );
    }

    public UserEntity from(UserDto userDto) {
        return new UserEntity(
                userDto.getId(),
                userDto.getName(),
                userDto.getRole()
        );
    }
}
