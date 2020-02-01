package ru.mano.aviasales.dto;

public abstract class BaseEntityDto {
    protected long id;

    public BaseEntityDto(long id) {
        this.id = id;
    }

    protected BaseEntityDto() {
    }
}
