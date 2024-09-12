package app.com._paws.domain.dtos;

import app.com._paws.domain.enums.Sex;
import app.com._paws.domain.enums.Specie;
import app.com._paws.domain.models.Pet;

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
) {

    public static PetDTO fromPet (Pet pet) {
        return new PetDTO(
                pet.getName(),
                pet.getWeight(),
                pet.getSex(),
                pet.getBreed(),
                pet.getSpecie(),
                pet.getBirthDate(),
                pet.getTutor().getId()
        );
    }
}