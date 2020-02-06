package ru.mano.aviasales.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import ru.mano.aviasales.entity.CityEntity;

@Component
public interface CityRepository extends JpaRepository<CityEntity, String> {
}
