package app.com._paws.domain.dtos.pet;

import app.com._paws.domain.enums.Sex;
import app.com._paws.domain.enums.Specie;
import app.com._paws.domain.models.Pet;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PetDTO {

    @JsonIgnore
    private UUID id;

    @Size(max = 50)
    @NotBlank(message = "O nome do Pet é obrigatório")
    private String name;

    @Positive(message = "O Peso do Pet deve ser maior que zero")
    private Double weight;

    @NotNull(message = "O Sexo do Pet é obrigatório")
    private Sex sex;

    @Size(max = 50)
    @NotNull(message = "A Raça do Pet é obrigatória")
    private String breed;

    @NotNull(message = "A Espécie do Pet é obrigatória")
    private Specie specie;

    @NotNull(message = "A Data de Nascimento do Pet é obrigatória")
    @PastOrPresent(message = "A Data de Nascimento deve ser atual ou passada")
    private LocalDate birthDate;

    @NotNull(message = "O Tutor é obrigatório")
    private UUID tutorId;

    public PetDTO(String name, Double weight, Sex sex, String breed, Specie specie, LocalDate birthDate, UUID tutorId) {
        this.name = name;
        this.weight = weight;
        this.sex = sex;
        this.breed = breed;
        this.specie = specie;
        this.birthDate = birthDate;
        this.tutorId = tutorId;
    }

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