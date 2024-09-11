package app.com._paws.domain.repositories;

import app.com._paws.domain.models.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PrescriptionRepository extends JpaRepository<Prescription, UUID> {

}