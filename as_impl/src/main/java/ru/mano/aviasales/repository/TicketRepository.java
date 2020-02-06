package ru.mano.aviasales.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import ru.mano.aviasales.entity.TicketEntity;

@Component
public interface TicketRepository extends JpaRepository<TicketEntity, String> {
}
