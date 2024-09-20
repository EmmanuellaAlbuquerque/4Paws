package app.com._paws.services;

import app.com._paws.domain.repositories.PrescriptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PrescriptionService {

    private final PrescriptionRepository prescriptionRepository;

    public void deleteById(UUID id) {
        this.prescriptionRepository.deleteById(id);
    }
}
