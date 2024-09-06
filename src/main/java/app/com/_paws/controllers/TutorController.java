package app.com._paws.controllers;

import app.com._paws.domain.dtos.TutorDTO;
import app.com._paws.domain.models.Tutor;
import app.com._paws.services.TutorService;
import app.com._paws.utils.RegistrationResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tutors")
public class TutorController {

    private final TutorService tutorService;

    @PostMapping("/new")
    public ResponseEntity<Object> registerTutor(@RequestBody TutorDTO tutorDTO) {

        Tutor tutor = tutorService.registerTutor(tutorDTO);

        return RegistrationResponseUtil.build(tutor);
    }
}