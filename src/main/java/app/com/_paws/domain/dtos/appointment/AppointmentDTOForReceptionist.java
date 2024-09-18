package app.com._paws.domain.dtos.appointment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
public class AppointmentDTOForReceptionist {
        @JsonIgnore
        private Integer id;

        @NotNull(message = "O Tipo de Consulta é obrigatório")
        private Integer appointmentType;

        @NotNull(message = "A Data da Consulta é obrigatória")
        @FutureOrPresent(message = "A Data da Consulta deve ser atual ou futura")
        private LocalDateTime scheduledDate;

        @NotEmpty(message = "Ao menos 1 Veterinário deve ser alocado para a Consulta")
        private List<UUID> veterinarians;

        @NotNull(message = "O Pet é obrigatório")
        private UUID pet;
}