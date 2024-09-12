package app.com._paws.domain.dtos;

import app.com._paws.domain.models.Address;
import app.com._paws.domain.models.Tutor;

import java.util.List;
import java.util.UUID;

public record TutorResponseDTO (
        UUID id,
        String name,
        String phone,
        String cpf,
        Address address,
        List<PetResponseDTO> pets
) {

    public static TutorResponseDTO fromTutor(Tutor tutor) {
        return new TutorResponseDTO(
                tutor.getId(),
                tutor.getName(),
                tutor.getPhone(),
                tutor.getCpf(),
                tutor.getAddress(),
                tutor.getPets().stream().map((pet) -> {
                    return new PetResponseDTO(
                            pet.getId(),
                            pet.getName()
                    );
                }).toList()
        );
    }
}