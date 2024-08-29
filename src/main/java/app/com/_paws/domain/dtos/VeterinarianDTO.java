package app.com._paws.domain.dtos;

import app.com._paws.domain.enums.Specialty;
import app.com._paws.domain.enums.UF;

public record VeterinarianDTO  (
        String email,
        String password,
        String name,
        String cpf,
        Specialty specialty,
        long crmv,
        UF uf
){}