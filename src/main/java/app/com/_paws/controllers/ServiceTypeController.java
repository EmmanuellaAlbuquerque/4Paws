package app.com._paws.controllers;

import app.com._paws.domain.dtos.DetailedServiceTypeResponseDTO;
import app.com._paws.domain.dtos.ServiceTypeDTO;
import app.com._paws.domain.dtos.ServiceTypeResponseDTO;
import app.com._paws.domain.models.AppointmentType;
import app.com._paws.domain.models.ExamType;
import app.com._paws.services.ServiceTypeService;
import app.com._paws.utils.RegistrationResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ServiceTypeController {
    private final ServiceTypeService serviceTypeService;

    @PostMapping("/service_types/appointments_types/new")
    public ResponseEntity<Object> registerAppointmentType(@RequestBody ServiceTypeDTO appointmentTypeDTO) {

        AppointmentType appointmentType = serviceTypeService.registerAppointmentType(appointmentTypeDTO);

        return RegistrationResponseUtil.build(appointmentType);
    }

    @PostMapping("/service_types/exams_types/new")
    public ResponseEntity<Object> registerExamType(@RequestBody ServiceTypeDTO examTypeDTO) {

        ExamType examType = serviceTypeService.registerExamType(examTypeDTO);

        return RegistrationResponseUtil.build(examType);
    }

    @GetMapping("/service_types/appointments_types")
    public ResponseEntity<List<ServiceTypeResponseDTO>> obtainAllAppointmentTypes() {

        return ResponseEntity.ok(this.serviceTypeService.findAllAppointmentTypes());
    }

    @GetMapping("/service_types/exams_types")
    public ResponseEntity<List<ServiceTypeResponseDTO>> obtainAllExamsTypes() {

        return ResponseEntity.ok(this.serviceTypeService.findAllExamsTypes());
    }

    @GetMapping("/service_types/appointments_types/{appointmentTypeId}")
    public ResponseEntity<DetailedServiceTypeResponseDTO> obtainOneAppointmentType(@PathVariable(value = "appointmentTypeId") Integer appointmentTypeId) {

        return ResponseEntity.ok(this.serviceTypeService.findOneAppointmentType(appointmentTypeId));
    }
}