package app.com._paws.domain.dtos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record AppointmentDTOForVeterinarian (

        @NotNull(message = "A Consulta vinculada é obrigatória")
        Integer id,

        @NotBlank(message = "A Anotação sobre a Consulta é obrigatória")
        String notes,

        @Valid
        List<ExamDTO> exams,

        @Valid
        List<PrescriptionDTO> prescriptions
) {}