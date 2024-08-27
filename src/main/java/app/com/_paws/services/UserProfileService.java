package app.com._paws.services;

import app.com._paws.domain.dtos.UserProfileDTO;
import app.com._paws.domain.models.Role;
import app.com._paws.domain.models.UserProfile;
import app.com._paws.domain.repositories.RoleRepository;
import app.com._paws.domain.repositories.UserProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserProfileService {

    private final UserProfileRepository userProfileRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserProfile registerUserProfile(UserProfileDTO userProfileDTO, String roleType) {
        userProfileDTO.setPassword(passwordEncoder.encode(userProfileDTO.getPassword()));
        UserProfile userProfile = new UserProfile(userProfileDTO);
        Optional<Role> roleOptional = roleRepository.findByName(roleType);

        if (roleOptional.isPresent()) {
            userProfile.setRole(roleOptional.get());
        }

        return this.userProfileRepository.save(userProfile);
    }
}
