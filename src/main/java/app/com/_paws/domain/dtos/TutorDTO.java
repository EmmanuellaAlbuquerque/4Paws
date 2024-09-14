package app.com._paws.domain.dtos;

import app.com._paws.domain.models.Address;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.NumberFormat;

public record TutorDTO (

        @NotBlank(message = "O nome é obrigatório")
        String name,

        @Pattern(regexp = "^\\(\\d{2}\\)\\s9\\d{4}-\\d{4}$"
                , message = "Formato de número de celular inválido. Tente o exemplo: (XX) 9XXXX-XXXX")
        @NotBlank(message = "O número de celular é obrigatório")
        String phone,

        @NotBlank(message = "O CPF é obrigatório")
        @CPF
        String cpf,

        @Valid
        @NotNull(message = "O Endereço é obrigatório")
        AddressDTO address
) {}