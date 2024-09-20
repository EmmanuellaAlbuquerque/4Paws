package app.com._paws.services;

import app.com._paws.domain.repositories.ExamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ExamService {

    private final ExamRepository examRepository;

    public void deleteById(UUID id) {
        this.examRepository.deleteById(id);
    }
}
