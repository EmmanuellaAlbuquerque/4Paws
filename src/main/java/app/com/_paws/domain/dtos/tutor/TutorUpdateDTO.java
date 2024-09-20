package app.com._paws.domain.dtos.tutor;

import app.com._paws.domain.dtos.AddressDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record TutorUpdateDTO(

        @Pattern(regexp = "^\\(\\d{2}\\)\\s9\\d{4}-\\d{4}$"
                , message = "Formato de número de celular inválido. Tente o exemplo: (XX) 9XXXX-XXXX")
        @NotBlank(message = "O número de celular é obrigatório")
        String phone,

        @Valid
        @NotNull(message = "O Endereço é obrigatório")
        AddressDTO address
) {}