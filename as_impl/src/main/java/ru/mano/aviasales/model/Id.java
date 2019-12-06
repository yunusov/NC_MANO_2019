package ru.mano.aviasales.model;

public abstract class Id {
    protected long id;

    public Id(long id) {
        this.id = id;
    }

    protected Id() {
    }
}
