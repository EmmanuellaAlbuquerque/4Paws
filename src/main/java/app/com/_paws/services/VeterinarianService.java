package app.com._paws.services;

import app.com._paws.domain.dtos.VeterinarianDTO;
import app.com._paws.domain.models.Appointment;
import app.com._paws.domain.models.Role;
import app.com._paws.domain.models.Veterinarian;
import app.com._paws.domain.repositories.RoleRepository;
import app.com._paws.domain.repositories.VeterinarianRepository;
import app.com._paws.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VeterinarianService {

    private final VeterinarianRepository veterinarianRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public Veterinarian registerVeterinarian(VeterinarianDTO veterinarianDTO) {
        Veterinarian veterinarian = new Veterinarian(veterinarianDTO);

        Optional<Role> roleOptional = roleRepository.findByName("ROLE_VETERINARIO");
        roleOptional.ifPresent(veterinarian::setRole);
        veterinarian.setPassword(passwordEncoder.encode(veterinarianDTO.password()));

        return this.veterinarianRepository.save(veterinarian);
    }

    public List<Appointment> findAllVetAppointments(UUID veterinarianUUID) {
        Veterinarian veterinarian = this.veterinarianRepository.findById(veterinarianUUID)
                .orElseThrow(() -> new NotFoundException("Veterinário não existente!"));

        List<Appointment> appointments = veterinarian.getAppointments();

        return appointments;
    }
}