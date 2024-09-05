package app.com._paws.controllers;

import app.com._paws.domain.dtos.TutorDTO;
import app.com._paws.domain.dtos.UserProfileDTO;
import app.com._paws.domain.dtos.VeterinarianDTO;
import app.com._paws.domain.models.Tutor;
import app.com._paws.domain.models.UserProfile;
import app.com._paws.services.TutorService;
import app.com._paws.services.UserProfileService;
import app.com._paws.services.VeterinarianService;
import app.com._paws.utils.RegistrationResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sign_up")
public class SignUpController {

    private final UserProfileService userProfileService;
    private final VeterinarianService veterinarianService;
    private final TutorService tutorService;

    @PostMapping("/receptionist")
    public ResponseEntity<Object> registerReceptionist(@RequestBody UserProfileDTO userProfileDTO) {
        UserProfile userProfile = userProfileService.registerUserProfile(userProfileDTO, "ROLE_RECEPCIONISTA");

        return RegistrationResponseUtil.build(userProfile);
    }

    @PostMapping("/admin")
    public ResponseEntity<Object> registerAdmin(@RequestBody UserProfileDTO userProfileDTO) {
        UserProfile userProfile = userProfileService.registerUserProfile(userProfileDTO, "ROLE_ADMIN");

        return RegistrationResponseUtil.build(userProfile);
    }

    @PostMapping("/veterinarian")
    public ResponseEntity<Object> registerVeterinarian(@RequestBody VeterinarianDTO veterinarianDTO) {
        UserProfileDTO userProfileDTO = new UserProfileDTO(
                veterinarianDTO.email(),
                veterinarianDTO.password(),
                veterinarianDTO.name(),
                veterinarianDTO.cpf()
        );

        UserProfile userProfile = userProfileService.registerUserProfile(
                userProfileDTO, "ROLE_VETERINARIO"
        );

        veterinarianService.registerVeterinarian(veterinarianDTO);

        return  RegistrationResponseUtil.build(userProfile);
    }

    @PostMapping("/tutor")
    public ResponseEntity<Object> registerTutor(@RequestBody TutorDTO tutorDTO) {

        Tutor tutor = tutorService.registerTutor(tutorDTO);

        return RegistrationResponseUtil.build(tutor);
    }
}
