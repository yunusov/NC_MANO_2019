package ru.mano.aviasales.entity;


import lombok.Data;

@Data
public class User extends AbstractEntityParent {
    private Role role;


    public User(int id, Role role) {
        super(id);
        this.role = role;
    }

    public enum Role {USER, ADMIN}
}
