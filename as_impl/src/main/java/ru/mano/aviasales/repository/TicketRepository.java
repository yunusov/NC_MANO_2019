package ru.mano.aviasales.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mano.aviasales.entity.City;
import ru.mano.aviasales.entity.Ticket;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    Long deleteBySourceAndDestination(City source, City destination);

    List<Ticket> findBySourceAndDestination(City source, City destination);
}
