package app.com._paws.controllers;

import app.com._paws.domain.dtos.AppointmentDTO;
import app.com._paws.domain.models.Appointment;
import app.com._paws.services.AppointmentService;
import app.com._paws.utils.RegistrationResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/appointments")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;

    @PostMapping("/new")
    public ResponseEntity<Object> registerAppointment(@RequestBody AppointmentDTO appointmentDTO) {

        Appointment appointment = this.appointmentService.registerAppointment(appointmentDTO);

        return RegistrationResponseUtil.build(appointment);
    }
}