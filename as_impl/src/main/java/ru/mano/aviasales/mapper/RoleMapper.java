package ru.mano.aviasales.mapper;

import ru.mano.aviasales.dto.Role;

import java.util.HashSet;
import java.util.Set;

public class RoleMapper {

    /**
     * cast set of entity Roles to single dto Role
     *
     * @param role set of entity Roles
     * @return dto Role
     */
    public static Role mapTo(Set<ru.mano.aviasales.entity.Role> role) {
        Role dtoRole;
        if (role.contains(ru.mano.aviasales.entity.Role.USER)) {
            dtoRole = Role.USER;
        } else if (role.contains(ru.mano.aviasales.entity.Role.ADMIN)) {
            dtoRole = Role.ADMIN;
        } else {
            dtoRole = Role.USER;
        }
        return dtoRole;
    }

    /**
     * cast dto Role to entity Role
     *
     * @param role dto Role
     * @return set of entity Roles
     */
    public static Set<ru.mano.aviasales.entity.Role> mapTo(Role role) {
        Set<ru.mano.aviasales.entity.Role> set = new HashSet<>();
        switch (role) {
            case USER:
                set.add(ru.mano.aviasales.entity.Role.USER);
            case ADMIN:
                set.add(ru.mano.aviasales.entity.Role.ADMIN);
            default:
                set.add(ru.mano.aviasales.entity.Role.USER);
        }
        return set;
    }

}
