package ru.mano.aviasales.entity;

public class User {
    private int userId;
    private Role role;

    public User(int userId) {
        this(userId, Role.USER);
    }

    public User(int userId, Role role) {
        this.userId = userId;
        this.role = role;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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
                "userId=" + userId +
                ", role=" + role +
                '}';
    }
}
