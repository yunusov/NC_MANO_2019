package ru.mano.aviasales.entity;


import lombok.Data;
import ru.mano.aviasales.dto.AbstractEntityParent;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class User {
    @Id
    private int id;
    private String name;
    private Role role;


    public User(int id, String name, Role role) {
        this.id = id;
        this.name = name;
        this.role = role;
    }

    public enum Role {USER, ADMIN}
}
