package app.com._paws.domain.dtos.pet;

import app.com._paws.domain.models.Pet;

import java.util.UUID;

public record PetResponseDTO(

        UUID id,
        String name
) {

    public static PetResponseDTO fromPet (Pet pet) {
        return new PetResponseDTO(
                pet.getId(),
                pet.getName()
        );
    }
}