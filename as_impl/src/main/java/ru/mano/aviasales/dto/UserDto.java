package ru.mano.aviasales.dto;


import lombok.Data;

@Data
public class UserDto extends BaseDto {
    private String name;
    private Role role;


    public UserDto(int id, String name, Role role) {
        super(id);
        this.name = name;
        this.role = role;
    }

    public enum Role {USER, ADMIN}
}
