package app.com._paws.domain.dtos;

import app.com._paws.domain.models.Address;
import app.com._paws.domain.models.Tutor;

import java.util.List;

public record TutorResponseDTO (
        String name,
        String phone,
        String cpf,
        Address address,
        List<PetResponseDTO> pets
) {

    public static TutorResponseDTO fromTutor(Tutor tutor) {
        return new TutorResponseDTO(
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