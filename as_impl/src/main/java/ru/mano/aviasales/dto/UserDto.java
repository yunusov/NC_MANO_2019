package ru.mano.aviasales.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import ru.mano.aviasales.entity.UserEntity;

@Data
@AllArgsConstructor
public class UserDto extends BaseDto {
    private String name;
    private Role role;

    public UserDto(String id, String name, Role role) {
        super(id);
        this.name = name;
        this.role = role;
    }


}
