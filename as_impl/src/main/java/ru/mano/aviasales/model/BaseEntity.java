package ru.mano.aviasales.model;

public abstract class BaseEntity {
    protected long id;

    public BaseEntity(long id) {
        this.id = id;
    }

    protected BaseEntity() {
    }
}
