package ru.mano.aviasales.entity;

import lombok.Data;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;


@Getter
@Data
@Entity
public class RouteEntity {
    @Id
    private int id;
    @ManyToOne
    private UserEntity userEntity;
    @OneToMany
    private List<TicketEntity> ticketEntities = new ArrayList<>();
}
