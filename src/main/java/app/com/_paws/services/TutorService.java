package app.com._paws.services;

import app.com._paws.domain.dtos.TutorDTO;
import app.com._paws.domain.dtos.TutorResponseDTO;
import app.com._paws.domain.models.Tutor;
import app.com._paws.domain.repositories.TutorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TutorService {

    private final TutorRepository tutorRepository;

    public Tutor registerTutor(TutorDTO tutorDTO) {
        Tutor tutor = new Tutor(tutorDTO);

        return this.tutorRepository.save(tutor);
    }

    public TutorResponseDTO findTutorByCpf(String cpf) {

        return TutorResponseDTO.fromTutor(this.tutorRepository.findByCpf(cpf));
    }
}