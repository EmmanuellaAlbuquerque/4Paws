package app.com._paws.controllers;

import app.com._paws.docs.TutorControllerDocs;
import app.com._paws.domain.dtos.tutor.TutorDTO;
import app.com._paws.domain.dtos.tutor.TutorResponseDTO;
import app.com._paws.domain.models.Tutor;
import app.com._paws.services.TutorService;
import app.com._paws.utils.RegistrationResponseUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/tutors", produces = {"application/json"})
public class TutorController implements TutorControllerDocs {

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