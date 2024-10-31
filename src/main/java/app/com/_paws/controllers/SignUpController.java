package app.com._paws.controllers;

import app.com._paws.docs.SignUpControllerDocs;
import app.com._paws.domain.dtos.UserProfileDTO;
import app.com._paws.domain.dtos.veterinarian.VeterinarianDTO;
import app.com._paws.domain.models.UserProfile;
import app.com._paws.domain.models.Veterinarian;
import app.com._paws.services.UserProfileService;
import app.com._paws.services.VeterinarianService;
import app.com._paws.utils.RegistrationResponseUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/sign-up", produces = {"application/json"})
public class SignUpController implements SignUpControllerDocs {

    private final UserProfileService userProfileService;
    private final VeterinarianService veterinarianService;

    @PostMapping("/receptionists")
    public ResponseEntity<Void> registerReceptionist(@Valid @RequestBody UserProfileDTO userProfileDTO) {
        UserProfile userProfile = userProfileService.registerUserProfile(userProfileDTO, "ROLE_RECEPCIONISTA");

        return RegistrationResponseUtil.build(userProfile);
    }

    @PostMapping("/admins")
    public ResponseEntity<Void> registerAdmin(@Valid @RequestBody UserProfileDTO userProfileDTO) {
        UserProfile userProfile = userProfileService.registerUserProfile(userProfileDTO, "ROLE_ADMIN");

        return RegistrationResponseUtil.build(userProfile);
    }

    @PostMapping("/veterinarians")
    public ResponseEntity<Void> registerVeterinarian(@Valid @RequestBody VeterinarianDTO veterinarianDTO) {
        Veterinarian veterinarian = veterinarianService.registerVeterinarian(veterinarianDTO);

        return  RegistrationResponseUtil.build(veterinarian);
    }
}