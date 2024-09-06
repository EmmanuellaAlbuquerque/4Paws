package app.com._paws.domain.repositories;

import app.com._paws.domain.models.ExamType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamTypeRepository extends JpaRepository<ExamType, Integer> {

}