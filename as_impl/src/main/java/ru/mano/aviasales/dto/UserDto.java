package ru.mano.aviasales.dto;

import lombok.Data;

@Data
public class UserDto extends BaseEntityDto {

    private String name;
    private Role role;

    public UserDto() {
    }

    public UserDto(long id, String name, Role role) {
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
