package app.com._paws.domain.dtos;

import app.com._paws.domain.models.Exam;
import app.com._paws.domain.models.Prescription;

import java.util.List;

public record AppointmentDTOForVeterinarian (
        Integer id,
        String notes,
        List<ExamDTO> exams,
        List<PrescriptionDTO> prescriptions
) {}