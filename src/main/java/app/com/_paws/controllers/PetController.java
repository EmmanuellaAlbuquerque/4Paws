package app.com._paws.controllers;

import app.com._paws.domain.dtos.PetDTO;
import app.com._paws.domain.models.Pet;
import app.com._paws.services.PetService;
import app.com._paws.utils.RegistrationResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pet")
public class PetController {

    private final PetService petService;

    @PostMapping("/new")
    public ResponseEntity<Object> registerPet(@RequestBody PetDTO petDTO) {

        Pet pet = this.petService.registerPet(petDTO);

        return RegistrationResponseUtil.build(pet);
    }
}