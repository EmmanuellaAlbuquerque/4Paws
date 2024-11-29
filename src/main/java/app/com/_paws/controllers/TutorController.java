package app.com._paws.controllers;

import app.com._paws.docs.TutorControllerDocs;
import app.com._paws.domain.dtos.tutor.TutorDTO;
import app.com._paws.domain.dtos.tutor.TutorResponseDTO;
import app.com._paws.domain.dtos.tutor.TutorUpdateDTO;
import app.com._paws.domain.dtos.tutor.TutorsDTO;
import app.com._paws.domain.models.Tutor;
import app.com._paws.services.TutorService;
import app.com._paws.utils.RegistrationResponseUtil;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.PagedModel;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

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

    @PutMapping("/{tutorId}")
    public ResponseEntity<Void> updateTutor(@PathVariable(value = "tutorId") UUID tutorId,
                                            @Valid @RequestBody TutorUpdateDTO tutorUpdateDTO) {

        this.tutorService.updateTutor(tutorId, tutorUpdateDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<PagedModel<TutorsDTO>> obtainAllTutors(@RequestParam("page") int pageIndex,
                                                             @Parameter(hidden = true) PagedResourcesAssembler assembler) {

        Page<TutorsDTO> tutorsPage = TutorsDTO.fromTutors(this.tutorService.findAllTutors(pageIndex));
        return ResponseEntity.ok(assembler.toModel(tutorsPage));
    }

    @GetMapping("/by-cpf")
    public ResponseEntity<TutorResponseDTO> obtainTutorByCpf(@RequestParam("tutorCpf") String tutorCpf) {

        TutorResponseDTO tutorResponseDTO = tutorService.findTutorByCpf(tutorCpf);

        return ResponseEntity.ok(tutorResponseDTO);
    }

    @GetMapping("/exists-by-cpf")
    public ResponseEntity<Void> tutorExistsByCpf(@RequestParam("tutorCpf") String tutorCpf) {

        tutorService.tutorExistsByCpf(tutorCpf);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/{tutorId}")
    public ResponseEntity<TutorResponseDTO> tutorExistsByCpf(@PathVariable(value = "tutorId") UUID tutorId) {
        return ResponseEntity.ok(tutorService.findTutorById(tutorId));
    }
}