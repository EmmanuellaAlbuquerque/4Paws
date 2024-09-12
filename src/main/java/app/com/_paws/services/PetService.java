package app.com._paws.services;

import app.com._paws.domain.dtos.PetDTO;
import app.com._paws.domain.models.Pet;
import app.com._paws.domain.models.Tutor;
import app.com._paws.domain.repositories.PetRepository;
import app.com._paws.domain.repositories.TutorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PetService {

    private final PetRepository petRepository;
    private final TutorRepository tutorRepository;

    public Pet registerPet(PetDTO petDTO){

        Tutor tutor = tutorRepository.findById(petDTO.tutorId())
                .orElseThrow(() -> new RuntimeException("Tutor não encontrado!"));

        Pet pet = new Pet(petDTO, tutor);

        return this.petRepository.save(pet);
    }

    public PetDTO findPetById(UUID petId) {

        Pet pet = this.petRepository.findById(petId)
                .orElseThrow(() -> new RuntimeException("Pet não encontrado!"));

        return PetDTO.fromPet(pet);
    }
}