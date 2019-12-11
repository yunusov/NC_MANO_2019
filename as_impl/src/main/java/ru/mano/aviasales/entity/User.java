package ru.mano.aviasales.entity;


import lombok.Data;

@Data
public class User extends AbstractEntityParent {
    private String name;
    private Role role;


    public User(int id, String name, Role role) {
        super(id);
        this.name = name;
        this.role = role;
    }

    public enum Role {USER, ADMIN}
}
