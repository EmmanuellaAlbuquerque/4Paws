package app.com._paws.services;

import app.com._paws.domain.models.Prescription;
import app.com._paws.domain.models.Veterinarian;
import app.com._paws.domain.repositories.PrescriptionRepository;
import app.com._paws.exceptions.BusinessException;
import app.com._paws.exceptions.JWTAuthException;
import app.com._paws.exceptions.NotFoundException;
import app.com._paws.exceptions.PermissionException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PrescriptionService {

    private final PrescriptionRepository prescriptionRepository;
    private final AuthService authService;

    public void deleteById(UUID id) {

        Prescription prescription = this.prescriptionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Prescrição não encontrada!"));

        UUID vetUUID = this.authService.obtainAuthenticatedUserUUID();
        List<Veterinarian> veterinarians = prescription.getAppointment().getVeterinarians();
        boolean isVetAuthorized = veterinarians
                .stream()
                .anyMatch(
                        veterinarian -> veterinarian.getId().equals(vetUUID)
                );

        if (isVetAuthorized) {
            this.prescriptionRepository.deleteById(id);
        }
        else {
            throw new PermissionException("Você não tem autorização para deletar essa Prescrição!");
        }

    }
}