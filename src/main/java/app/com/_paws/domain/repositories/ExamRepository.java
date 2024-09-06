package app.com._paws.domain.repositories;

import app.com._paws.domain.models.Exam;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ExamRepository extends JpaRepository<Exam, UUID> {

}