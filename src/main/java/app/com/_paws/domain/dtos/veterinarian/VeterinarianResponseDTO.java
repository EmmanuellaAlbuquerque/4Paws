package app.com._paws.domain.dtos.veterinarian;

import app.com._paws.domain.enums.Specialty;
import app.com._paws.domain.enums.UF;
import app.com._paws.domain.models.Veterinarian;
import org.springframework.data.domain.Page;

import java.util.UUID;

public record VeterinarianResponseDTO (

        UUID id,
        String name,
        Specialty specialty,
        Long crmv,
        UF uf
) {

    public static Page<VeterinarianResponseDTO> fromVet(Page<Veterinarian> allVeterinarians) {
        return allVeterinarians.map(veterinarian -> {
            return new VeterinarianResponseDTO(
                    veterinarian.getId(),
                    veterinarian.getName(),
                    veterinarian.getSpecialty(),
                    veterinarian.getCrmv(),
                    veterinarian.getUf()
            );
        });
    }
}