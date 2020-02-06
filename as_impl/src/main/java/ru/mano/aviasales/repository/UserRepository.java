package ru.mano.aviasales.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import ru.mano.aviasales.entity.UserEntity;

@Component
public interface UserRepository extends JpaRepository<UserEntity, String> {
}