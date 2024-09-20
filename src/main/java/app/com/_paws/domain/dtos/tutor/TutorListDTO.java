package app.com._paws.domain.dtos.tutor;

import app.com._paws.domain.models.Tutor;

import java.util.List;
import java.util.UUID;

public record TutorListDTO (

        UUID id,
        String name,
        String phone,
        String cpf
) {

    public static List<TutorListDTO> fromTutors(List<Tutor> tutors) {

        return tutors.stream().map((tutor -> {
            return new TutorListDTO(
                    tutor.getId(),
                    tutor.getName(),
                    tutor.getPhone(),
                    tutor.getCpf()
            );
        })).toList();
    }
}