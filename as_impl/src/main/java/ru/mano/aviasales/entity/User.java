package ru.mano.aviasales.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(catalog = "postgres", schema = "aviato", name = "users")
public class User {

    @Id
    private long id;

    @Getter
    @Setter
    @Column
    private String name;

    @Getter
    @Setter
    private Role role;

    public User() {
    }

    public User(long id, String name, Role role) {
        this.id = id;
        this.name = name;
        this.role = role;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", role=" + role +
                '}';
    }
}