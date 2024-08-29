package app.com._paws.services;

import app.com._paws.domain.dtos.VeterinarianDTO;
import app.com._paws.domain.models.Veterinarian;
import app.com._paws.domain.repositories.VeterinarianRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VeterinarianService {

    private final VeterinarianRepository veterinarianRepository;

    public void registerVeterinarian(VeterinarianDTO veterinarianDTO) {
        Veterinarian veterinarian = new Veterinarian(veterinarianDTO);

        this.veterinarianRepository.save(veterinarian);
    }
}