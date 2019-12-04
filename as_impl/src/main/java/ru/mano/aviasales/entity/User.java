package ru.mano.aviasales.entity;

public class User {
    private User id;
    private Role role;

    public User(int userId) {
        this(userId, Role.USER);
    }

    public User(int userId, Role role) {
        this.id = userId;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public enum Role {ADMIN, USER} //Получется user с ролью user...

    @Override
    public String toString() {
        return "User{" +
                "userId=" + id +
                ", role=" + role +
                '}';
    }
}
