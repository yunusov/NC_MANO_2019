package ru.ncedu.project.dto;

public abstract class AbstractEntityParent {
    int id;

    public AbstractEntityParent() {
    }

    public AbstractEntityParent(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
