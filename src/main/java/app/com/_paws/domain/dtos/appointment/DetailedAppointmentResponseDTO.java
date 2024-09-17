package app.com._paws.domain.dtos;

import app.com._paws.domain.models.Appointment;
import app.com._paws.domain.models.Exam;
import app.com._paws.domain.models.Pet;
import app.com._paws.domain.models.Prescription;

import java.time.LocalDateTime;
import java.util.List;

public record DetailedAppointmentResponseDTO(
        Integer id,
        LocalDateTime scheduledDate,
        List<String> veterinarians,
        DetailedPetResponseDTO pet,
        String notes,
        String appointmentType,
        List<ExamResponseDTO> exams,
        List<Prescription> prescriptions
) {

    public static DetailedAppointmentResponseDTO getDetailedAppointmentDTOFromAppointment(Appointment appointment) {
        return new DetailedAppointmentResponseDTO(
                appointment.getId(),
                appointment.getScheduledDate(),
                appointment.getVeterinarians().stream().map((veterinarian -> {
                    return veterinarian.getName();
                })).toList(),
                DetailedPetResponseDTO.fromPet(appointment.getPet()),
                appointment.getNotes(),
                appointment.getAppointmentType().getName(),
                appointment.getExams().stream()
                        .map((exam) -> {
                            return new ExamResponseDTO(
                                    exam.getId(),
                                    exam.getResult(),
                                    exam.getScheduledDate(),
                                    exam.getExamType().getName()
                            );
                        }).toList(),
                appointment.getPrescriptions()
        );
    }
}