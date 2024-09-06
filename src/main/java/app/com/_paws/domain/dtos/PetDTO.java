package app.com._paws.domain.dtos;

import app.com._paws.domain.enums.Sex;
import app.com._paws.domain.enums.Specie;

import java.time.LocalDate;
import java.util.UUID;

public record PetDTO(

        String name,
        Double weight,
        Sex sex,
        String breed,
        Specie specie,
        LocalDate birthDate,
        UUID tutorId
) {}