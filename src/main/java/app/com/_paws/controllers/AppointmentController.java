package app.com._paws.controllers;

import app.com._paws.docs.AppointmentControllerDocs;
import app.com._paws.domain.dtos.appointment.AppointmentDTOForReceptionist;
import app.com._paws.domain.dtos.appointment.AppointmentResponseDTO;
import app.com._paws.domain.models.Appointment;
import app.com._paws.services.AppointmentService;
import app.com._paws.utils.RegistrationResponseUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/appointments", produces = {"application/json"})
@RequiredArgsConstructor
public class AppointmentController implements AppointmentControllerDocs {

    private final AppointmentService appointmentService;

    @PostMapping("/new")
    public ResponseEntity<Void> receptionistRegisterAppointment(@Valid @RequestBody AppointmentDTOForReceptionist appointmentDTOForReceptionist) {

        Appointment appointment = this.appointmentService.receptionistRegisterAppointment(appointmentDTOForReceptionist);

        return RegistrationResponseUtil.build(appointment);
    }

    @GetMapping
    public ResponseEntity<List<AppointmentResponseDTO>> obtainAllAppointments() {
        List<Appointment> appointments = this.appointmentService.findAllAppointments();

        return ResponseEntity.ok(AppointmentResponseDTO.fromAppointmentList(appointments));
    }

    @DeleteMapping("/{appointmentId}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable(value = "appointmentId") Integer appointmentId) {

        this.appointmentService.deleteAppointmentById(appointmentId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{appointmentId}")
    public ResponseEntity<Void> updateAppointment(@PathVariable(value = "appointmentId") Integer appointmentId,
                                                  @Valid @RequestBody AppointmentDTOForReceptionist appointmentDTOForReceptionist) {

        this.appointmentService.updateAppointmentById(appointmentId, appointmentDTOForReceptionist);
        return ResponseEntity.ok().build();
    }
}