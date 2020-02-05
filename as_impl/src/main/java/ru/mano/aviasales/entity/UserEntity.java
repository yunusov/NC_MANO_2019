package ru.mano.aviasales.entity;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class UserEntity {
    @Id
    private int id;

    private String name;

    @Enumerated(EnumType.ORDINAL)
    private Role role;



    public enum Role {USER, ADMIN}
}
