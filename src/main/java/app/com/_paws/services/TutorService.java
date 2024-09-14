package app.com._paws.services;

import app.com._paws.domain.dtos.TutorDTO;
import app.com._paws.domain.dtos.TutorResponseDTO;
import app.com._paws.domain.models.Tutor;
import app.com._paws.domain.repositories.TutorRepository;
import app.com._paws.exceptions.BusinessException;
import app.com._paws.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TutorService {

    private final TutorRepository tutorRepository;

    public Tutor registerTutor(TutorDTO tutorDTO) {
        this.tutorRepository.findByCpf(tutorDTO.cpf())
                .ifPresent((tutor -> {
                    throw new BusinessException("'" + tutor.getCpf() + "'" + " - CPF já está em uso!");
                }));

        Tutor tutor = new Tutor(tutorDTO);

        return this.tutorRepository.save(tutor);
    }

    public TutorResponseDTO findTutorByCpf(String cpf) {

        Tutor tutor = this.tutorRepository.findByCpf(cpf)
                .orElseThrow(() -> new NotFoundException("CPF não encontrado!"));

        return TutorResponseDTO.fromTutor(tutor);
    }
}