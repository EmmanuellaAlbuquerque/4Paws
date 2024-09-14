package app.com._paws.domain.dtos;

import jakarta.validation.constraints.NotBlank;

public record PrescriptionDTO (

        @NotBlank(message = "A Medicação é obrigatória")
        String medicine,

        @NotBlank(message = "A Descrição referente a Dosagem da Medicação é obrigatória")
        String dosageDescription
) {}