package app.com._paws.domain.dtos.appointment;

import app.com._paws.domain.models.Appointment;

import java.time.LocalDateTime;
import java.util.List;

    public record AppointmentResponseDTO(
        Integer id,
        LocalDateTime scheduledDate,
        List<String> veterinarians,
        String pet,
        String tutor,
        String appointmentType
) {

    public static List<AppointmentResponseDTO> fromAppointmentList(List<Appointment> appointments) {
        return appointments.stream()
                .map((appointment) -> AppointmentResponseDTO.fromAppointment(appointment))
                .toList();
    }

    public static AppointmentResponseDTO fromAppointment(Appointment appointment) {
        return new AppointmentResponseDTO(
            appointment.getId(),
            appointment.getScheduledDate(),
            appointment.getVeterinarians().stream().map((veterinarian -> {
                return veterinarian.getName();
            })).toList(),
            appointment.getPet().getName(),
            appointment.getPet().getTutor().getName(),
            appointment.getAppointmentType().getName()
        );
    }
}