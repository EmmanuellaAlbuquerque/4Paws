package app.com._paws.domain.repositories;

import app.com._paws.domain.models.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

    List<Appointment> findByVeterinariansIdAndScheduledDateGreaterThanEqualOrderByScheduledDate(UUID veterinarianId, LocalDateTime today);
}