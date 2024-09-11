package app.com._paws.domain.dtos;

public record DetailedServiceTypeResponseDTO (
        Integer id,
        String name,
        String description,
        Double basePrice
) {}