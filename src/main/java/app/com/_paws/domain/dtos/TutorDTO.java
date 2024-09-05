package app.com._paws.domain.dtos;

import app.com._paws.domain.models.Address;

public record TutorDTO (
        String name,
        String phone,
        Address address
) {}