package ru.mano.aviasales.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mano.aviasales.entity.CityEntity;

public interface CityRepository extends JpaRepository<CityEntity, String> {
}
