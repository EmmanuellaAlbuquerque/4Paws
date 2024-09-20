package app.com._paws.services;

import app.com._paws.domain.dtos.tutor.TutorDTO;
import app.com._paws.domain.dtos.tutor.TutorResponseDTO;
import app.com._paws.domain.dtos.tutor.TutorUpdateDTO;
import app.com._paws.domain.models.Address;
import app.com._paws.domain.models.Tutor;
import app.com._paws.domain.repositories.TutorRepository;
import app.com._paws.exceptions.BusinessException;
import app.com._paws.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

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

    public void updateTutor(UUID tutorId, TutorUpdateDTO tutorUpdateDTO) {

        Tutor tutor = this.tutorRepository.findById(tutorId)
                .orElseThrow(() -> new NotFoundException("Tutor não encontrado!"));

        tutor.setPhone(tutorUpdateDTO.phone());

        Address address = tutor.getAddress();
        address.setStreet(tutorUpdateDTO.address().street());
        address.setNumber(tutorUpdateDTO.address().number());
        address.setNeighborhood(tutorUpdateDTO.address().neighborhood());
        tutor.setAddress(address);

        this.tutorRepository.save(tutor);
    }

    public Page<Tutor> findAllTutors(int pageIndex) {
        return this.tutorRepository.findAll(
                PageRequest.of(
                        pageIndex,
                        10,
                        Sort.by(Sort.Direction.ASC, "name")
                )
        );
    }
}