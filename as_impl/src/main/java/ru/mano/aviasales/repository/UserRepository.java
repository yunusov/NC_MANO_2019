package ru.mano.aviasales.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import ru.mano.aviasales.entity.UserEntity;

@Component
@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {
}