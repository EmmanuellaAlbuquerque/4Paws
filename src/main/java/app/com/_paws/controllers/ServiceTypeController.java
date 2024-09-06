package app.com._paws.controllers;

import app.com._paws.domain.dtos.ServiceTypeDTO;
import app.com._paws.domain.models.AppointmentType;
import app.com._paws.domain.models.ExamType;
import app.com._paws.services.ServiceTypeService;
import app.com._paws.utils.RegistrationResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ServiceTypeController {
    private final ServiceTypeService serviceTypeService;

    @PostMapping("/appointment_type/new")
    public ResponseEntity<Object> registerAppointmentType(@RequestBody ServiceTypeDTO appointmentTypeDTO) {

        AppointmentType appointmentType = serviceTypeService.registerAppointmentType(appointmentTypeDTO);

        return RegistrationResponseUtil.build(appointmentType);
    }

    @PostMapping("/exame_type/new")
    public ResponseEntity<Object> registerExamType(@RequestBody ServiceTypeDTO examTypeDTO) {

        ExamType examType = serviceTypeService.registerExamType(examTypeDTO);

        return RegistrationResponseUtil.build(examType);
    }
}