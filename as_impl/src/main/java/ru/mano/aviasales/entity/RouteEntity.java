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
}
