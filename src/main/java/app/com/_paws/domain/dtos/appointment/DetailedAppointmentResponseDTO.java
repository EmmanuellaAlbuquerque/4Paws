package app.com._paws.domain.dtos.appointment;

import app.com._paws.domain.dtos.PrescriptionDTO;
import app.com._paws.domain.dtos.pet.DetailedPetResponseDTO;
import app.com._paws.domain.dtos.exam.ExamResponseDTO;
import app.com._paws.domain.models.Appointment;

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
        List<PrescriptionDTO> prescriptions
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
                                    exam.getExamType().getName(),
                                    exam.getExamType().getId()
                            );
                        }).toList(),
                appointment.getPrescriptions().stream()
                        .map(prescription -> {
                            return new PrescriptionDTO(
                                    prescription.getId(),
                                    prescription.getMedicine(),
                                    prescription.getDosageDescription()
                            );
                        }).toList()
        );
    }
}