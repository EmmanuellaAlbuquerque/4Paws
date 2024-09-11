package app.com._paws.domain.dtos;

public record ServiceTypeDTO (
    String name,
    String description,
    Double basePrice
) {}