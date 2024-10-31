package app.com._paws.services;

import app.com._paws.domain.dtos.IUserProfile;
import app.com._paws.domain.dtos.UserProfileDTO;
import app.com._paws.domain.models.Role;
import app.com._paws.domain.models.UserProfile;
import app.com._paws.domain.repositories.RoleRepository;
import app.com._paws.domain.repositories.UserProfileRepository;
import app.com._paws.exceptions.BusinessException;
import app.com._paws.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserProfileService {

    private final UserProfileRepository userProfileRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserProfile registerUserProfile(UserProfileDTO userProfileDTO, String roleType) {
        this.userProfileRepository.findByCpf(userProfileDTO.getCpf())
                .ifPresent((userProfile -> {
                    throw new BusinessException("'" + userProfile.getCpf() + "'" + " - CPF já está em uso!");
                }));

        this.userProfileRepository.findByEmail(userProfileDTO.getEmail())
                .ifPresent((userProfile -> {
                    throw new BusinessException("'" + userProfile.getEmail() + "'" + " - Email já está em uso!");
                }));

        userProfileDTO.setPassword(passwordEncoder.encode(userProfileDTO.getPassword()));
        UserProfile userProfile = new UserProfile(userProfileDTO);
        Optional<Role> roleOptional = roleRepository.findByName(roleType);

        if (roleOptional.isPresent()) {
            userProfile.setRole(roleOptional.get());
        }

        return this.userProfileRepository.save(userProfile);
    }

    public IUserProfile obtainUserProfileInfo(UUID userProfileUUID) {

        UserProfile userProfile = this.userProfileRepository.findById(userProfileUUID)
                .orElseThrow(() -> new NotFoundException("Profile não encontrado!"));

        return userProfile.getDto();
    }
}