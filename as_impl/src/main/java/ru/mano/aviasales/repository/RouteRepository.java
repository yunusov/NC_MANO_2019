package ru.mano.aviasales.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mano.aviasales.entity.Route;

@Repository
public interface RouteRepository extends JpaRepository<Route, Long> {
}
