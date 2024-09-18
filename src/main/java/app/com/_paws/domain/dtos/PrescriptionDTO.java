package app.com._paws.domain.dtos;

import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record PrescriptionDTO (

        UUID id,

        @NotBlank(message = "A Medicação é obrigatória")
        String medicine,

        @NotBlank(message = "A Descrição referente a Dosagem da Medicação é obrigatória")
        String dosageDescription
) {}