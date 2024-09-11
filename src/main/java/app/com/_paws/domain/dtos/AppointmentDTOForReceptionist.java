package app.com._paws.domain.dtos;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record AppointmentDTOForReceptionist(
        Integer appointmentType,
        LocalDateTime scheduledDate,
        List<UUID> veterinarians,
        UUID pet
) {}