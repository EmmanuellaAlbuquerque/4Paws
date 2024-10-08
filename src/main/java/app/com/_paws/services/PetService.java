package app.com._paws.services;

import app.com._paws.domain.dtos.pet.DetailedPetResponseDTO;
import app.com._paws.domain.dtos.pet.PetDTO;
import app.com._paws.domain.models.Pet;
import app.com._paws.domain.models.Tutor;
import app.com._paws.domain.repositories.PetRepository;
import app.com._paws.domain.repositories.TutorRepository;
import app.com._paws.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PetService {

    private final PetRepository petRepository;
    private final TutorRepository tutorRepository;

    public Pet registerPet(PetDTO petDTO){

        Tutor tutor = tutorRepository.findById(petDTO.getTutorId())
                .orElseThrow(() -> new NotFoundException("Tutor não encontrado!"));

        Pet pet = new Pet(petDTO, tutor);

        return this.petRepository.save(pet);
    }

    public DetailedPetResponseDTO findPetById(UUID petId) {

        Pet pet = this.petRepository.findById(petId)
                .orElseThrow(() -> new NotFoundException("Pet não encontrado!"));

        return DetailedPetResponseDTO.fromPet(pet);
    }

    public void updatePet(UUID id, PetDTO petDTO) {

        if (this.petRepository.existsById(id)) {
            petDTO.setId(id);
            this.registerPet(petDTO);
        }
        else {
            throw new NotFoundException("Pet não encontrado!");
        }
    }
}