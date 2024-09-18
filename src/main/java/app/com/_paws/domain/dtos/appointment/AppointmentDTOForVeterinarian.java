package app.com._paws.domain.dtos.appointment;

import app.com._paws.domain.dtos.exam.ExamDTO;
import app.com._paws.domain.dtos.PrescriptionDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record AppointmentDTOForVeterinarian (

        @NotBlank(message = "A Anotação sobre a Consulta é obrigatória")
        String notes,

        @Valid
        List<ExamDTO> exams,

        @Valid
        List<PrescriptionDTO> prescriptions
) {}