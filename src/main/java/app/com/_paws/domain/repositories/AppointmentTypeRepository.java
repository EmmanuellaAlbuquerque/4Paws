package app.com._paws.domain.repositories;

import app.com._paws.domain.models.AppointmentType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppointmentTypeRepository extends JpaRepository<AppointmentType, Integer> {

    Optional<AppointmentType> findByName(String name);
}