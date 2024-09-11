package app.com._paws.domain.repositories;

import app.com._paws.domain.models.AppointmentType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentTypeRepository extends JpaRepository<AppointmentType, Integer> {

}