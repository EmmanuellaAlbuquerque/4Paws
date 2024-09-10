package app.com._paws.domain.dtos;

import java.time.LocalDateTime;

public record ExamResponseDTO (
        String result,
        LocalDateTime scheduledDate,
        String examType
) {}