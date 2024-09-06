package app.com._paws.domain.dtos;

import app.com._paws.domain.models.AppointmentType;
import app.com._paws.domain.models.Exam;
import app.com._paws.domain.models.Prescription;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record AppointmentDTO(
        Integer appointmentType,
        LocalDateTime scheduledDate,
        List<UUID> veterinarians,
        UUID pet,
        String notes,
        List<Integer> examTypes,
        List<Prescription> prescriptions
) {}