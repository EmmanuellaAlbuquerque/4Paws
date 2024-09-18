package app.com._paws.domain.dtos.pet;

import app.com._paws.domain.enums.Sex;
import app.com._paws.domain.enums.Specie;
import app.com._paws.domain.models.Pet;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.UUID;

public record PetDTO(

        @Size(max = 50)
        @NotBlank(message = "O nome do Pet é obrigatório")
        String name,

        @Positive(message = "O Peso do Pet deve ser maior que zero")
        Double weight,

        @NotNull(message = "O Sexo do Pet é obrigatório")
        Sex sex,

        @Size(max = 50)
        @NotNull(message = "A Raça do Pet é obrigatória")
        String breed,

        @NotNull(message = "A Espécie do Pet é obrigatória")
        Specie specie,

        @NotNull(message = "A Data de Nascimento do Pet é obrigatória")
        @PastOrPresent(message = "A Data de Nascimento deve ser atual ou passada")
        LocalDate birthDate,

        @NotNull(message = "O Tutor é obrigatório")
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