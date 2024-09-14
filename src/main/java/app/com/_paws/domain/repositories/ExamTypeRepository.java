package app.com._paws.domain.repositories;

import app.com._paws.domain.models.ExamType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ExamTypeRepository extends JpaRepository<ExamType, Integer> {

    Optional<ExamType> findByName(String name);
}