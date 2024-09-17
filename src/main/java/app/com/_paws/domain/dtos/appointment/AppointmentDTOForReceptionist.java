package app.com._paws.domain.dtos;

import jakarta.validation.constraints.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record AppointmentDTOForReceptionist(

        @NotNull(message = "O Tipo de Consulta é obrigatório")
        Integer appointmentType,

        @NotNull(message = "A Data da Consulta é obrigatória")
        @FutureOrPresent(message = "A Data da Consulta deve ser atual ou futura")
        LocalDateTime scheduledDate,

        @NotEmpty(message = "Ao menos 1 Veterinário deve ser alocado para a Consulta")
        List<UUID> veterinarians,

        @NotNull(message = "O Pet é obrigatório")
        UUID pet
) {}