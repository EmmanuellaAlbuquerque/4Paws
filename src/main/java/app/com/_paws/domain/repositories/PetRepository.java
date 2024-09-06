package app.com._paws.domain.repositories;

import app.com._paws.domain.models.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PetRepository extends JpaRepository<Pet, UUID> {

}