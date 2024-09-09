package app.com._paws.services;

import app.com._paws.domain.dtos.VeterinarianDTO;
import app.com._paws.domain.models.Appointment;
import app.com._paws.domain.models.Veterinarian;
import app.com._paws.domain.repositories.VeterinarianRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VeterinarianService {

    private final VeterinarianRepository veterinarianRepository;

    public void registerVeterinarian(VeterinarianDTO veterinarianDTO) {
        Veterinarian veterinarian = new Veterinarian(veterinarianDTO);

        this.veterinarianRepository.save(veterinarian);
    }

    public List<Appointment> findAllVetAppointments(UUID veterinarianUUID) {
        Veterinarian veterinarian = this.veterinarianRepository.findById(veterinarianUUID)
                .orElseThrow(() -> new RuntimeException("Veterinário não existente!"));

        List<Appointment> appointments = veterinarian.getAppointments();

        return appointments;
    }
}