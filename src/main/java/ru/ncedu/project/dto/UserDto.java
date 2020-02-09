package ru.ncedu.project.dto;

import lombok.Data;

@Data
public class UserDto extends AbstractEntityParent {
    private String name;
    private UserType userType;


    public UserDto(int id, String name, UserType userType) {
        super(id);
        this.name = name;
        this.userType = userType;
    }

    public enum UserType {ADMIN, USER}
}
