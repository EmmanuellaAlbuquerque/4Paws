package app.com._paws.domain.dtos.veterinarian;

import app.com._paws.domain.dtos.IUserProfile;
import app.com._paws.domain.enums.Specialty;
import app.com._paws.domain.enums.UF;

public record VeterinarianProfileResponseDTO (

        String email,

        String name,

        String cpf,

        String role,

        Specialty specialty,

        long crmv,

        UF uf

) implements IUserProfile {
}