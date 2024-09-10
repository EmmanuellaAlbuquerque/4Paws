package app.com._paws.controllers;

import app.com._paws.domain.models.Appointment;
import app.com._paws.domain.models.UserProfile;
import app.com._paws.services.AppointmentService;
import app.com._paws.services.VeterinarianService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("veterinarian")
@RequiredArgsConstructor
public class VeterinarianController {

    private final VeterinarianService veterinarianService;
    private final AppointmentService appointmentService;

    @GetMapping("/appointments")
    public ResponseEntity<List<Appointment>> obtainAllVetAppointments() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object authenticatedVetUUIDString = authentication.getPrincipal();
        UUID vetUUID = UUID.fromString((String) authenticatedVetUUIDString);

        List<Appointment> appointments = this.appointmentService.findAllAppointmentsByVeterinarian(vetUUID);

        return ResponseEntity.ok(appointments);
    }
}
