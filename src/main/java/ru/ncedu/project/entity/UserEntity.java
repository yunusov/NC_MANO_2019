package ru.ncedu.project.entity;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class UserEntity {
    @Id
    private int id;
    private String name;
    private UserType userType;


    public UserEntity(int id, String name, UserType userType) {
        this.id = id;
        this.name = name;
        this.userType = userType;
    }

    public enum UserType {ADMIN, USER}
}