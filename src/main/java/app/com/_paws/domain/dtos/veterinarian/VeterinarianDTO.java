package app.com._paws.domain.dtos.veterinarian;

import app.com._paws.domain.enums.Specialty;
import app.com._paws.domain.enums.UF;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

public record VeterinarianDTO  (

        @Email(message = "Formato de e-mail inválido")
        @NotBlank(message = "O email é obrigatório")
        String email,

        @NotBlank(message = "A senha é obrigatória")
        String password,

        @NotBlank(message = "O nome é obrigatório")
        String name,

        @NotBlank(message = "O CPF é obrigatório")
        @CPF
        String cpf,

        Specialty specialty,

        @NotNull(message = "O Número do CRMV é obrigatório")
        long crmv,

        @NotNull(message = "A UF (Unidade Federativa) é obrigatória")
        UF uf
){}