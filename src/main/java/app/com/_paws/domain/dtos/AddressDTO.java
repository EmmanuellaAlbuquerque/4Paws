package app.com._paws.domain.dtos;

import app.com._paws.domain.models.Address;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record AddressDTO (

        @NotBlank(message = "O Bairro é obrigatório")
        String neighborhood,

        @Positive(message = "O Número da Casa deve ser maior que zero")
        @NotNull(message = "O Número da Casa é obrigatório")
        Long number,

        @NotBlank(message = "A Rua é obrigatória")
        String street
) {}
