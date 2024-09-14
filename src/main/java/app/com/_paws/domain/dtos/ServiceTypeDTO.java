package app.com._paws.domain.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ServiceTypeDTO (

        @NotBlank(message = "O nome é obrigatório")
        String name,

        String description,

        @Positive(message = "O Preço Base deve ser maior que zero")
        @NotNull(message = "O Preço Base é obrigatório")
        Double basePrice
) {}