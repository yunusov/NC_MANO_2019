package ru.mano.aviasales.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Data
@Entity
@AllArgsConstructor
public class RouteEntity {
    @Id
    private String id;
    @ManyToOne
    private UserEntity userEntity;
    @OneToMany
    private List<TicketEntity> tickets = new ArrayList<>();

    public RouteEntity() {
    }

    public RouteEntity(UserEntity userEntity, List<TicketEntity> tickets) {
        this.id = UUID.randomUUID().toString();
        this.userEntity = userEntity;
        this.tickets = tickets;
    }
}