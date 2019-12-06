package ru.mano.aviasales.model;

import lombok.Getter;
import lombok.Setter;

public class User extends BaseEntity {
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private Role role;

    public User(long id, String name, Role role) {
        this.id = id;
        this.name = name;
        this.role = role;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
