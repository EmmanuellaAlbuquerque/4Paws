package app.com._paws.domain.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;

public record ExamDTO(

        @NotNull(message = "O Tipo de Exame é obrigatório")
        Integer examTypeId
) {}