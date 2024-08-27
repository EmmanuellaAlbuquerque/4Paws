package app.com._paws.controllers;

import app.com._paws.domain.dtos.UserProfileDTO;
import app.com._paws.domain.models.UserProfile;
import app.com._paws.services.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sign_up")
public class SignUpController {

    private final UserProfileService userProfileService;

    @PostMapping("/receptionist")
    public ResponseEntity<Object> registerReceptionist(@RequestBody UserProfileDTO userProfileDTO) {
        UserProfile userProfile = userProfileService.registerUserProfile(userProfileDTO, "ROLE_RECEPCIONISTA");

        return this.buildRegistrationResponse(userProfile);
    }

    @PostMapping("/admin")
    public ResponseEntity<Object> registerAdmin(@RequestBody UserProfileDTO userProfileDTO) {
        UserProfile userProfile = userProfileService.registerUserProfile(userProfileDTO, "ROLE_ADMIN");

        return this.buildRegistrationResponse(userProfile);
    }

    private ResponseEntity<Object> buildRegistrationResponse(UserProfile userProfile) {
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(userProfile.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }
}
