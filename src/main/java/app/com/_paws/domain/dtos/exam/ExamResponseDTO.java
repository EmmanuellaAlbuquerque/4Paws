package app.com._paws.domain.dtos.exam;

import java.time.LocalDateTime;
import java.util.UUID;

public record ExamResponseDTO (
        UUID id,
        String result,
        LocalDateTime scheduledDate,
        String examType,
        Integer examTypeId
) {}