package app.com._paws.controllers;

import app.com._paws.domain.dtos.AppointmentResponseDTO;
import app.com._paws.domain.dtos.DetailedAppointmentResponseDTO;
import app.com._paws.domain.models.Appointment;
import app.com._paws.domain.models.UserProfile;
import app.com._paws.services.AppointmentService;
import app.com._paws.services.AuthService;
import app.com._paws.services.VeterinarianService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("veterinarian")
@RequiredArgsConstructor
public class VeterinarianController {

    private final VeterinarianService veterinarianService;
    private final AppointmentService appointmentService;
    private final AuthService authService;

    @GetMapping("/appointments")
    public ResponseEntity<List<AppointmentResponseDTO>> obtainAllVetAppointments() {

        UUID vetUUID = this.authService.obtainAuthenticatedUserUUID();

        List<Appointment> appointments = this.appointmentService.findAllAppointmentsByVeterinarian(vetUUID);
        List<AppointmentResponseDTO> appointmentResponseDTO = AppointmentResponseDTO.fromAppointmentList(appointments);

        return ResponseEntity.ok(appointmentResponseDTO);
    }

    @GetMapping("/appointments/{appointmentId}")
    public ResponseEntity<DetailedAppointmentResponseDTO> obtainOneVetAppointment(@PathVariable(value = "appointmentId") Integer appointmentId) {

        UUID vetUUID = this.authService.obtainAuthenticatedUserUUID();
        Appointment appointment = this.appointmentService.findAppointmentByIdAndVeterinarianId(vetUUID, appointmentId);

        return ResponseEntity.ok(DetailedAppointmentResponseDTO.getDetailedAppointmentDTOFromAppointment(appointment));
    }
}