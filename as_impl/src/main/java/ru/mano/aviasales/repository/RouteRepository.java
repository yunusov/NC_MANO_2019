package ru.mano.aviasales.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import ru.mano.aviasales.entity.RouteEntity;

@Component
public interface RouteRepository extends JpaRepository<RouteEntity, String> {
}
