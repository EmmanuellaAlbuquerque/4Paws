package app.com._paws.services;

import app.com._paws.domain.models.Exam;
import app.com._paws.domain.models.Veterinarian;
import app.com._paws.domain.repositories.ExamRepository;
import app.com._paws.exceptions.NotFoundException;
import app.com._paws.exceptions.PermissionException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ExamService {

    private final ExamRepository examRepository;
    private final AuthService authService;

    public void deleteById(UUID id) {

        Exam exam = this.examRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Exame não encontrado!"));

        UUID vetUUID = this.authService.obtainAuthenticatedUserUUID();
        List<Veterinarian> veterinarians = exam.getAppointment().getVeterinarians();
        boolean isVetAuthorized = veterinarians
                .stream()
                .anyMatch(
                        veterinarian -> veterinarian.getId().equals(vetUUID)
                );

        if (isVetAuthorized) {
            this.examRepository.deleteById(id);
        }
        else {
            throw new PermissionException("Você não tem autorização para deletar esse Exame!");
        }
    }
}
