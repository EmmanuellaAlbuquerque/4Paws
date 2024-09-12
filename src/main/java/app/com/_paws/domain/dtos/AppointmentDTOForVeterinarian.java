package app.com._paws.domain.dtos;

import java.util.List;

public record AppointmentDTOForVeterinarian (
        Integer id,
        String notes,
        List<ExamDTO> exams,
        List<PrescriptionDTO> prescriptions
) {}