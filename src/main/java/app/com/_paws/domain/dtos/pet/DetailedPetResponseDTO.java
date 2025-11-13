package app.com._paws.domain.dtos.pet;

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
        String sex,
        String breed,
        String specie,
        Integer age,
        UUID tutorId,
        LocalDate birthDate
) {

    public static DetailedPetResponseDTO fromPet (Pet pet) {
        return new DetailedPetResponseDTO(
                pet.getId(),
                pet.getName(),
                pet.getWeight(),
                pet.getSex().getName(),
                pet.getBreed(),
                pet.getSpecie().getName(),
                Period.between(pet.getBirthDate(), LocalDate.now()).getYears(),
                pet.getTutor().getId(),
                pet.getBirthDate()
        );
    }
}