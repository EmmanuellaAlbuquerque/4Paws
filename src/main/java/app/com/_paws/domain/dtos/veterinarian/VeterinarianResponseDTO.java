package app.com._paws.domain.dtos.veterinarian;

import app.com._paws.domain.enums.Specialty;
import app.com._paws.domain.enums.UF;
import app.com._paws.domain.models.Veterinarian;

import java.util.List;
import java.util.UUID;

public record VeterinarianResponseDTO (

        UUID id,
        String name,
        Specialty specialty,
        Long crmv,
        UF uf
) {
    public static List<VeterinarianResponseDTO> fromVet(List<Veterinarian> allVeterinarians) {
        return allVeterinarians.stream().map((veterinarian -> {
            return new VeterinarianResponseDTO(
                    veterinarian.getId(),
                    veterinarian.getName(),
                    veterinarian.getSpecialty(),
                    veterinarian.getCrmv(),
                    veterinarian.getUf()
            );
        })).toList();
    }
}