package app.com._paws.controllers;

import app.com._paws.docs.UserProfileControllerDocs;
import app.com._paws.domain.dtos.IUserProfile;
import app.com._paws.services.AuthService;
import app.com._paws.services.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1/profiles", produces = {"application/json"})
@RequiredArgsConstructor
public class UserProfileController implements UserProfileControllerDocs {

    private final AuthService authService;
    private final UserProfileService userProfileService;

    @GetMapping
    public ResponseEntity<IUserProfile> obtainUserProfile() {

        UUID userProfileUUID = this.authService.obtainAuthenticatedUserUUID();

        return ResponseEntity.ok(this.userProfileService.obtainUserProfileInfo(userProfileUUID));
    }
}