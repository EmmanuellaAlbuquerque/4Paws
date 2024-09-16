package app.com._paws.controllers;

import app.com._paws.docs.VeterinarianControllerDocs;
import app.com._paws.domain.dtos.AppointmentDTOForVeterinarian;
import app.com._paws.domain.dtos.AppointmentResponseDTO;
import app.com._paws.domain.dtos.DetailedAppointmentResponseDTO;
import app.com._paws.domain.models.Appointment;
import app.com._paws.services.AppointmentService;
import app.com._paws.services.AuthService;
import app.com._paws.services.VeterinarianService;
import app.com._paws.utils.RegistrationResponseUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1/veterinarians", produces = {"application/json"})
@RequiredArgsConstructor
public class VeterinarianController implements VeterinarianControllerDocs {

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

        DetailedAppointmentResponseDTO detailedAppointmentResponseDTO = DetailedAppointmentResponseDTO.getDetailedAppointmentDTOFromAppointment(appointment);
        return ResponseEntity.ok(detailedAppointmentResponseDTO);
    }

    @PutMapping("/appointments/{appointmentId}")
    public ResponseEntity<Object> veterinarianUpdateAppointment(@Valid @RequestBody AppointmentDTOForVeterinarian appointmentDTOForVeterinarian) {

        UUID vetUUID = this.authService.obtainAuthenticatedUserUUID();
        Appointment appointment = this.appointmentService.veterinarianUpdateAppointment(appointmentDTOForVeterinarian, vetUUID);

        return RegistrationResponseUtil.build(appointment);
    }
}