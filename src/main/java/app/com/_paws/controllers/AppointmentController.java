package app.com._paws.controllers;

import app.com._paws.domain.dtos.AppointmentDTOForReceptionist;
import app.com._paws.domain.dtos.AppointmentDTOForVeterinarian;
import app.com._paws.domain.models.Appointment;
import app.com._paws.services.AppointmentService;
import app.com._paws.services.AuthService;
import app.com._paws.utils.RegistrationResponseUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/appointments")
@RequiredArgsConstructor
@Validated
public class AppointmentController {

    private final AppointmentService appointmentService;

    @PostMapping("/new")
    public ResponseEntity<Object> receptionistRegisterAppointment(@Valid @RequestBody AppointmentDTOForReceptionist appointmentDTOForReceptionist) {

        Appointment appointment = this.appointmentService.receptionistRegisterAppointment(appointmentDTOForReceptionist);

        return RegistrationResponseUtil.build(appointment);
    }
}