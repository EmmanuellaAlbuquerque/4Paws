package app.com._paws.controllers;

import app.com._paws.domain.dtos.TutorDTO;
import app.com._paws.domain.dtos.TutorResponseDTO;
import app.com._paws.domain.models.Tutor;
import app.com._paws.services.TutorService;
import app.com._paws.utils.RegistrationResponseUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tutors")
public class TutorController {

    private final TutorService tutorService;

    @PostMapping("/new")
    public ResponseEntity<Object> registerTutor(@Valid @RequestBody TutorDTO tutorDTO) {

        Tutor tutor = tutorService.registerTutor(tutorDTO);

        return RegistrationResponseUtil.build(tutor);
    }

    @GetMapping
    public ResponseEntity<TutorResponseDTO> obtainTutorByCpf(@RequestParam("tutorCpf") String tutorCpf) {

        TutorResponseDTO tutorResponseDTO = tutorService.findTutorByCpf(tutorCpf);

        return ResponseEntity.ok(tutorResponseDTO);
    }
}