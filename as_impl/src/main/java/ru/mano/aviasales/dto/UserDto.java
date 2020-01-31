package ru.mano.aviasales.dto;


import lombok.Data;

import javax.persistence.Entity;

@Data
public class UserDto extends AbstractEntityParent {
    private String name;
    private Role role;


    public UserDto(int id, String name, Role role) {
        super(id);
        this.name = name;
        this.role = role;
    }

    public enum Role {USER, ADMIN}
}
