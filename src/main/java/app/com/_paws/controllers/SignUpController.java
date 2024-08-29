package app.com._paws.controllers;

import app.com._paws.domain.dtos.UserProfileDTO;
import app.com._paws.domain.models.UserProfile;
import app.com._paws.services.UserProfileService;
import app.com._paws.utils.RegistrationResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sign_up")
public class SignUpController {

    private final UserProfileService userProfileService;

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
}
