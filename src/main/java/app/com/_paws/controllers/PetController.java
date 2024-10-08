package app.com._paws.controllers;

import app.com._paws.docs.PetControllerDocs;
import app.com._paws.domain.dtos.pet.DetailedPetResponseDTO;
import app.com._paws.domain.dtos.pet.PetDTO;
import app.com._paws.domain.models.Pet;
import app.com._paws.services.PetService;
import app.com._paws.utils.RegistrationResponseUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/pets", produces = {"application/json"})
public class PetController implements PetControllerDocs {

    private final PetService petService;

    @PostMapping("/new")
    public ResponseEntity<Object> registerPet(@Valid @RequestBody PetDTO petDTO) {

        Pet pet = this.petService.registerPet(petDTO);

        return RegistrationResponseUtil.build(pet);
    }

    @PutMapping("/{petId}")
    public ResponseEntity<Void> updatePet(@PathVariable(value = "petId") UUID petId, @Valid @RequestBody PetDTO petDTO) {

        this.petService.updatePet(petId, petDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{petId}")
    public ResponseEntity<DetailedPetResponseDTO> obtainOnePet(@PathVariable(value = "petId") UUID petId) {

        return ResponseEntity.ok(this.petService.findPetById(petId));
    }
}