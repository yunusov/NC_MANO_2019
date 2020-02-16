package ru.ncedu.project.entity;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class RouteEntity {
    @Id
    private int id;
    private UserEntity userEntityId;
    @OneToMany
    private List<TicketEntity> ticketEntities = new ArrayList<>();

    public RouteEntity(int id, UserEntity userEntityId, TicketEntity ticketEntity) {
        this.id = id;
        this.userEntityId = userEntityId;
        this.ticketEntities.add(ticketEntity);
    }
    public RouteEntity(int id, UserEntity userEntityId, List<TicketEntity> ticketEntities) {
        this.id = id;
        this.userEntityId = userEntityId;
        this.ticketEntities = ticketEntities;
    }

    public RouteEntity(int id, UserEntity userEntityId) {
        this.id = id;
        this.userEntityId = userEntityId;
    }

    public List<TicketEntity> getTicketList() {
        return ticketEntities;
    }


    public boolean addTicket(TicketEntity ticketEntity) {
        return this.ticketEntities.add(ticketEntity);
    }

    public boolean addTicket(List<TicketEntity> ticketEntities) {
        return this.ticketEntities.addAll(ticketEntities);
    }

}