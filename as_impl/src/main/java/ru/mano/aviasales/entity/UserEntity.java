package ru.mano.aviasales.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import ru.mano.aviasales.dto.Role;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
public class UserEntity {
    @Id
    private String id;

    private String name;

    @Enumerated(EnumType.ORDINAL)
    private Role role;


}
