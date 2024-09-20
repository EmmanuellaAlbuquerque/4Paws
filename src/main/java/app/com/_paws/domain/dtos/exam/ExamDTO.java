package app.com._paws.domain.dtos.exam;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.UUID;

public record ExamDTO(

        UUID id,

        @NotNull(message = "O Tipo de Exame é obrigatório")
        Integer examTypeId,

        String result,

        @FutureOrPresent(message = "A Data da Consulta deve ser atual ou futura")
        LocalDateTime scheduledDate
) {}