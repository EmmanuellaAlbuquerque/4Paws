package app.com._paws.domain.dtos;

import app.com._paws.domain.enums.Sex;
import app.com._paws.domain.enums.Specie;
import app.com._paws.domain.models.Pet;

import java.time.LocalDate;
import java.time.Period;
import java.util.UUID;

public record DetailedPetResponseDTO (
        UUID id,
        String name,
        Double weight,
        Sex sex,
        String breed,
        Specie specie,
        Integer age,
        UUID tutorId
) {

    public static DetailedPetResponseDTO fromPet (Pet pet) {
        return new DetailedPetResponseDTO(
                pet.getId(),
                pet.getName(),
                pet.getWeight(),
                pet.getSex(),
                pet.getBreed(),
                pet.getSpecie(),
                Period.between(pet.getBirthDate(), LocalDate.now()).getYears(),
                pet.getTutor().getId()
        );
    }
}