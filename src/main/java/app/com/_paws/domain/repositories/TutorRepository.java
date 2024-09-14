package app.com._paws.domain.repositories;

import app.com._paws.domain.models.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TutorRepository extends JpaRepository<Tutor, UUID> {

    public Optional<Tutor> findByCpf(String cpf);
}