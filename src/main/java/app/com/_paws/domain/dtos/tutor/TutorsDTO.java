package app.com._paws.domain.dtos.tutor;

import app.com._paws.domain.models.Tutor;
import org.springframework.data.domain.Page;

import java.util.UUID;

public record TutorsDTO (

        UUID id,
        String name,
        String phone,
        String cpf
) {

    public static Page<TutorsDTO> fromTutors(Page<Tutor> tutorsPage) {

        return tutorsPage.map(tutor -> {
            return new TutorsDTO(
                    tutor.getId(),
                    tutor.getName(),
                    tutor.getPhone(),
                    tutor.getCpf()
            );
        });
    }
}