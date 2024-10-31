package app.com._paws.controllers;

import app.com._paws.docs.ServiceTypeControllerDocs;
import app.com._paws.domain.dtos.DetailedServiceTypeResponseDTO;
import app.com._paws.domain.dtos.servicetype.ServiceTypeDTO;
import app.com._paws.domain.dtos.servicetype.ServiceTypeResponseDTO;
import app.com._paws.domain.models.AppointmentType;
import app.com._paws.domain.models.ExamType;
import app.com._paws.services.ServiceTypeService;
import app.com._paws.utils.RegistrationResponseUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1", produces = {"application/json"})
@RequiredArgsConstructor
public class ServiceTypeController implements ServiceTypeControllerDocs {
    private final ServiceTypeService serviceTypeService;

    @PostMapping("/service-types/appointments-types/new")
    public ResponseEntity<Void> registerAppointmentType(@Valid @RequestBody ServiceTypeDTO appointmentTypeDTO) {

        AppointmentType appointmentType = serviceTypeService.registerAppointmentType(appointmentTypeDTO);

        return RegistrationResponseUtil.build(appointmentType);
    }

    @PostMapping("/service-types/exams-types/new")
    public ResponseEntity<Void> registerExamType(@Valid @RequestBody ServiceTypeDTO examTypeDTO) {

        ExamType examType = serviceTypeService.registerExamType(examTypeDTO);

        return RegistrationResponseUtil.build(examType);
    }

    @GetMapping("/service-types/appointments-types")
    public ResponseEntity<List<ServiceTypeResponseDTO>> obtainAllAppointmentTypes() {

        return ResponseEntity.ok(this.serviceTypeService.findAllAppointmentTypes());
    }

    @GetMapping("/service-types/exams-types")
    public ResponseEntity<List<ServiceTypeResponseDTO>> obtainAllExamsTypes() {

        return ResponseEntity.ok(this.serviceTypeService.findAllExamsTypes());
    }

    @GetMapping("/service-types/appointments-types/{appointmentTypeId}")
    public ResponseEntity<DetailedServiceTypeResponseDTO> obtainOneAppointmentType(@PathVariable(value = "appointmentTypeId") Integer appointmentTypeId) {

        return ResponseEntity.ok(this.serviceTypeService.findOneAppointmentType(appointmentTypeId));
    }

    @GetMapping("/service-types/exams-types/{examTypeId}")
    public ResponseEntity<DetailedServiceTypeResponseDTO> obtainOneExamType(@PathVariable(value = "examTypeId") Integer examTypeId) {

        return ResponseEntity.ok(this.serviceTypeService.findOneExamType(examTypeId));
    }

    @PutMapping("/service-types/appointments-types/{appointmentTypeId}")
    public ResponseEntity<Void> updateAppointmentType(@PathVariable(value = "appointmentTypeId") Integer appointmentTypeId,
                                                      @Valid @RequestBody ServiceTypeDTO appointmentTypeDTO) {

        this.serviceTypeService.updateAppointmentType(appointmentTypeId, appointmentTypeDTO);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/service-types/exams-types/{examTypeId}")
    public ResponseEntity<Void> updateExamType(@PathVariable(value = "examTypeId") Integer examTypeId,
                                               @Valid @RequestBody ServiceTypeDTO examTypeDTO) {

        this.serviceTypeService.updateExamType(examTypeId, examTypeDTO);
        return ResponseEntity.ok().build();
    }
}