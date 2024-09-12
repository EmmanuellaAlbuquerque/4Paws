package app.com._paws.controllers;

import app.com._paws.domain.dtos.PetDTO;
import app.com._paws.domain.models.Pet;
import app.com._paws.services.PetService;
import app.com._paws.utils.RegistrationResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pets")
public class PetController {

    private final PetService petService;

    @PostMapping("/new")
    public ResponseEntity<Object> registerPet(@RequestBody PetDTO petDTO) {

        Pet pet = this.petService.registerPet(petDTO);

        return RegistrationResponseUtil.build(pet);
    }

    @GetMapping("/{petId}")
    public ResponseEntity<PetDTO> obtainOnePet(@PathVariable(value = "petId") UUID petId) {

        return ResponseEntity.ok(this.petService.findPetById(petId));
    }
}